grammar Piece;

@header {
package com.team3316.orchestra.antlr;
import com.team3316.orchestra.pitch.Pitch;
import org.apache.commons.numbers.fraction.Fraction;
}

piece : decls voices EOF;

decls : decl* ;
decl : storageDecl | tuningDecl;

storageDecl : '\\storage' storage ;
storage : 'note' | 'pitch' | 'frequency' ;

tuningDecl : '\\tuning' STRING namedNote pitch ;

voices : voice ('\\\\' voice)* ;
voice : music ;

music : relativeMusic | fixedMusic | seqMusic | musicAtom;
relativeMusic : '\\relative' music | '\\relative' namedNote music ;
fixedMusic : '\\fixed' music | '\\fixed' namedNote music;
seqMusic : '{' music* '}';
musicAtom : timedNN  | timedPitch;

timedPitch : pitch | pitch ':' dur;
timedNN : namedNote | namedNote dur;

namedNote : NOTE_NAME octave;

dur returns[Fraction result] : INT { $result = Fraction.of($INT.int); }
    | inner=dur '*' INT { $result = $inner.result.multiply($INT.int); };

octave returns[int result] : TICKS { $result = $TICKS.text.length(); }
    | COMMAS { $result = -$COMMAS.text.length(); }
    | { $result = 0; };

pitch returns[Pitch result] : frequency { $result = Pitch.of($frequency.result); }
    | inner=pitch '*' ratio { $result = $inner.result.upRatio($ratio.result); }
    | inner=pitch '+' ratio { $result = $inner.result.upSemis($ratio.result); };
ratio returns[Fraction result] : INT { $result = Fraction.of($INT.int); }
    | num=INT '/' den=INT { $result = Fraction.of($num.int, $den.int); };
frequency returns[double result] : INT { $result = (double) $INT.int; }
    | FLOAT { $result = Double.parseDouble($FLOAT.text); };

TICKS: '\''+;
COMMAS: ','+;
NOTE_NAME : 'c' | 'cis' | 'ces' | 'cih' | 'ceh' | 'cisis' | 'ceses' | 'cihih' | 'ceheh'
    | 'd' | 'dis' | 'des' | 'dih' | 'deh' | 'disis' | 'deses' | 'dihih' | 'deheh'
    | 'e' | 'eis' | 'es' | 'ees' | 'eih' | 'eh' | 'eeh' | 'eisis' | 'eses' | 'eeses' | 'eihih' | 'eheh' | 'eeheh'
    | 'f' | 'fis' | 'fes' | 'fih' | 'feh' | 'fisis' | 'feses' | 'fihih' | 'feheh'
    | 'g' | 'gis' | 'ges' | 'gih' | 'geh' | 'gisis' | 'geses' | 'gihih' | 'geheh'
    | 'a' | 'ais' | 'as' | 'aes' | 'aih' | 'ah' | 'aeh' | 'aisis' | 'ases' | 'aeses' | 'aihih' | 'aheh' | 'aeheh'
    | 'b' | 'bis' | 'bes' | 'bih' | 'beh' | 'bisis' | 'beses' | 'bihih' | 'beheh';
ID: [a-zA-Z]+;
FLOAT: [1-9][0-9]*'.'[0-9]+;
INT: [1-9][0-9]*;
STRING : '"' (~'"')* '"';
SPACES : [ \t\r\n]+ -> skip;
