package jabberpoint;

import jabberpoint.creators.TextItemCreator;
import jabberpoint.presentationComponents.slideItems.BitmapItem;
import jabberpoint.presentationComponents.Presentation;
import jabberpoint.presentationComponents.Slide;
import jabberpoint.style.Style;
import jabberpoint.style.StyleFactory;

import java.awt.*;

/** A built in demo-presentation
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation extends Accessor {

	public void loadFile(Presentation presentation, String unusedFilename) {
		presentation.setTitle("Demo presentationComponents.Presentation");
		Slide slide;
		slide = new Slide();
		slide.setTitle("JabberPoint");
		StyleFactory.getStyle("default", 12, Color.black, new Font("Comic Sans", Font.PLAIN, 12), 12, 10);
		new TextItemCreator().setStyle("default").setText("The Java presentationComponents.Presentation Tool!").appendToSlide(slide);
		presentation.append(slide);
	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Save As->Demo! called");
	}
}
