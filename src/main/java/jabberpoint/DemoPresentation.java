package jabberpoint;

import jabberpoint.creators.BitmapItemCreator;
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
		StyleFactory.getStyle("default", 12, Color.black, new Font("Comic Sans MS", Font.PLAIN, 12), 50, 10);
		StyleFactory.getStyle("cursive", 12, Color.black, new Font("Forte", Font.ITALIC, 12), 50, 10);
		StyleFactory.getStyle("IMPACT", 12, Color.black, new Font("Impact", Font.BOLD, 12), 50, 10);
		StyleFactory.getStyle("impactRed", 12, Color.red, new Font("Impact", Font.PLAIN, 12), 50, 10);
		StyleFactory.getStyle("impactGreen", 12, Color.green, new Font("Impact", Font.PLAIN, 12), 50, 10);
		StyleFactory.getStyle("impactBlue", 12, Color.blue, new Font("Impact", Font.PLAIN, 12), 50, 10);


		new TextItemCreator().setStyle("default").setText("The Java presentationComponents.Presentation Tool!").appendToSlide(slide);

		new TextItemCreator().setStyle("cursive").setText("Cursive edition!").setCoords(10, 200).appendToSlide(slide);

		new TextItemCreator().setStyle("IMPACT").setText("IMPACT EDITION!").setCoords(10, 400).appendToSlide(slide);


		new TextItemCreator().setStyle("IMPACT").setText("And now on the right side!!").setCoords(500, 250).appendToSlide(slide);

		presentation.append(slide);


		slide = new Slide();

		new TextItemCreator().setStyle("impactRed").setLevel(-1).setText("levels just work!").setCoords(30, 300).appendToSlide(slide);
		new TextItemCreator().setStyle("impactGreen").setLevel(0).setText("levels just work!").setCoords(35, 300).appendToSlide(slide);
		new TextItemCreator().setStyle("impactBlue").setLevel(1).setText("levels just work!").setCoords(40, 300).appendToSlide(slide);

		new BitmapItemCreator().setImage("serclogo_fc.jpg").setScale(2).setCoords(600, 400).appendToSlide(slide);

		new TextItemCreator().setStyle("default").setText("images too!~").setCoords(600, 370).setLevel(1).appendToSlide(slide);

		presentation.append(slide);




	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Save As->Demo! called");
	}
}
