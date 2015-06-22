package study.circuit

object CircuitWS {
	def afterDelay(until: Int)(body: => Unit) {
		body
		if (until > 1) afterDelay(until - 1)(body)
	}                                         //
                                                  //> afterDelay: (until: Int)(body: => Unit)Unit
	
	afterDelay(5)(println("test"))            //> test
                                                  //| test
                                                  //| test
                                                  //| test
                                                  //| test
}