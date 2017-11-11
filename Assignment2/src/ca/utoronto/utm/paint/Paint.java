package ca.utoronto.utm.paint;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Paint {
	public static void main(String[] args) {
		
		// redirect all stderr to stdout to see IDE output easier
				System.setErr(System.out);
				
				// set JUL log format
				// 1$ = time stamp
				// 2$ = source
				// 4$ = log level
				// 5$ = log message
				System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] %2$s: %5$s%n");
//				System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc [%4$s] %2$s: %5$s%n");
				
			    // Get the root logger and set log level of it handlers to FINEST to show all levels
			    Logger rootLogger = Logger.getLogger("");
			    for(Handler handler : rootLogger.getHandlers()) {
			      // Change log level of default handler(s) of root logger
			      // The paranoid would check that this is the ConsoleHandler ;)
			      handler.setLevel(Level.FINEST); // in case we specify more logger that has finer log
			    }
			    
			    // Set log level
			    Logger logger = Logger.getLogger(Paint.class.getPackage().getName());
			    logger.setLevel(Level.FINE);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Paint();
			}
		});
	}

	PaintModel model; // Model
	View view; // View+Controller

	public Paint() {
		// Create MVC components and hook them together

		// Model
		this.model = new PaintModel();

		// View+Controller
		this.view = new View(model);
		
	}
}
