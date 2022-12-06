package iwwwdnw.comp1;

import iwwwdnw.comp1.impl.I1Impl;
import iwwwdnw.comp1.port.I1;
import iwwwdnw.comp1.port.PortA;

class Facade implements Factory, PortA, I1 {

	
	
	/**
	 * @directed true
	 * @link composition
	 */
	
	private I1Impl i1;

	@Override
	public PortA portA() {
		return this;
	}

	@Override
	public I1 i1() {
		if (this.i1 == null)
			this.i1 = new I1Impl();
		return this;
	}
}
