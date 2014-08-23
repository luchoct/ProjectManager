package com.luisgal.proman

enum Phase {
  BRIEFING('briefing'), SCOPING('scoping'), INTERACTION('interaction'), DEVELOPMENT('development'), QA('qa'), RELEASE('release')
  
  private final String code;
  Phase(final String code) { this.code = code }
  
  def getPhaseCodes() {
    return [Phase.code]
  }
}

