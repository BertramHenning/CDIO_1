package validation;

public interface IException {
		
	public class InpExc extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1234567890;

		public InpExc(String msg, Throwable e) {
			super(msg,e);
		}

		public InpExc(String msg) {
			super(msg);
		}

	}
}
