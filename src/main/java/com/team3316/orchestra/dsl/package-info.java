/**
 * DBugOrchestra DSL
 * <p>
 * This is a loose subset of <a href="https://lilypond.org">Lilypond</a> syntax. Example:
 * <pre>
 * \tuning "Equal31" a' 440
 * \storage note
 * \tempo 4 = 124
 * \relative c' {
 *     c4 d e c |
 *     c d e c |
 *     e f g2 |
 *     e4 f g2 |
 *     g8 a g f e4 c |
 *     g'8 a g f e4 c |
 *     c g c2 |
 *     c4 g c2
 * }
 * \\
 * {
 *     e'8 e'16 b e'8 fis' gis'8 gis'16 e' fis'8 a' |
 *     gis'4 b' gis' b'
 * }
 * \\
 * \relative c' {
 *     c4+8 d8 e4. c8 |
 *     e4 c e2 |
 *     d4*3/2 e8 f f e d |
 *     f1 |
 *     e4. f8 g4. e8 |
 *     g4 e g2 |
 *     f4. g8 a a g f |
 *     a1
 * }
 * </pre>
 */
package com.team3316.orchestra.dsl;
