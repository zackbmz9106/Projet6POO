import org.eclipse.swt.widgets.Composite;

public class main extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public main(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}