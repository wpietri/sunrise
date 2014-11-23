package light

import org.scalatest.matchers.{MatchResult, Matcher}

trait LightMatchers {
  def equal(expected: LightOutput) = new LightOutputMatcher(expected)

  class LightOutputMatcher(expected: LightOutput) extends Matcher[LightOutput] {

    override def apply(actual: LightOutput): MatchResult = {
      MatchResult((actual.x - expected.x).abs < 0.001 &&
        (actual.y - expected.y).abs < 0.001 &&
        (actual.lumens - expected.lumens < 1),
        s"""$actual did not equal $expected"""",
        s"""$actual equals $expected""""
      )
    }
  }

}
