import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.font.*;
import java.awt.Desktop;
import java.io.File;
class LineDisplay{

	Scanner scan = new Scanner(System.in);
	//initializing the line starting cordinates
	int x = 100;
	int y = 90;
	int[] relArray;
	int yWindow = 140;
	boolean xMove = false;
	String neuralCode;
	//NC Validation
	char checker;
	double checkerNum = 0;
	double checkerCount = 0;
	boolean validNC = false;

	//ex: String neuralCode = "1000,0100,1100,0001,1100,1110";
	String[] neuralStrings;// = neuralCode.split(",");
	int relations;// = neuralStrings[0].length();

	//initializing a list of colors to use
	private final List<Color> colors;
	Color background = new Color(255,255,234);
	//creating a "pointer" for list of colors, indexes start at 0
	int lineColor = 0;

	//this creates the "canvas" that we will be editing, outside of the methods for global editing
    public LineDisplay(String sneuralCode){
    	neuralCode = sneuralCode;
		neuralStrings = neuralCode.split(",");
		relations = neuralStrings[0].length();
		System.out.print("Inside class");
		//this algorithm test to see if there is a valid neural code
		while (validNC == false){
			for(int i = 0; i < neuralCode.length(); i++){
				if( neuralCode.charAt(i) == ','){
					checkerCount++;
				}
				if( neuralCode.charAt(i) != ',' && neuralCode.charAt(i) != '0' && neuralCode.charAt(i) != '1'){
					System.out.print("Invalid neural code, Try again\n");
					neuralCode = scan.nextLine();
					checkerNum = 0;
					checkerCount = 0;
					checker = '0';
					i = 0;
				}
			}
			while (checker != ',' && checkerNum != neuralCode.length()){
				checker = neuralCode.charAt((int)checkerNum);
				checkerNum++;
			}
			if (checker == ','){
				checkerNum--;
				if ((neuralCode.length() - checkerCount) / (checkerCount+1) == checkerNum){
					validNC = true;
				}
				else{
					System.out.print("Invalid neural code, Try again\n");
					neuralCode = scan.nextLine();
					checkerNum = 0;
					checkerCount = 0;
					checker = '0';
				}
			}
			else{
				validNC = true;
			}

		}



		//Splits the NC into seperate Strings
		neuralStrings = neuralCode.split(",");
		relations = neuralStrings[0].length();

		//creating the color list for the color coded display of lines
		colors = createColorList();

		for(int i = 0; i < relations; i++){
			yWindow = yWindow + 30;
		}
    }



	//list of colors to uses, feel free to edit/delete/add as desired
	private List<Color> createColorList() {
	        List<Color> list = new ArrayList<>();
	        list.add(Color.RED);
	        list.add(Color.YELLOW);
			list.add(Color.GREEN);
	        list.add(Color.MAGENTA);
	        list.add(Color.CYAN);
	        list.add(Color.GRAY);
	        list.add(Color.BLUE);
	        list.add(Color.ORANGE);
	       	return list;
    }

    public void paint() {
		BufferedImage image = new BufferedImage(180+(neuralStrings.length*50),yWindow-30, BufferedImage.TYPE_INT_RGB);
		Graphics2D g3 = image.createGraphics();

		//g3.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		//g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g3.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		//g3.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		//g3.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		//g3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		//g3.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		//g3.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		try{

		Rectangle2D rectBackground = new Rectangle2D.Double(0, 0, 180+(neuralStrings.length*50), yWindow-30);
		//Color background = new Color(255,255,204);
		g3.setColor(Color.WHITE);
		g3.fill(rectBackground);
		g3.setColor(Color.lightGray);
		g3.setStroke(new BasicStroke(12));
		g3.draw(rectBackground);
		g3.setStroke(new BasicStroke(2));






		//algorithm for sorting the line by relations
		relArray = new int[neuralStrings.length];
		for (int i = 0; i < neuralStrings.length; i++){

		}




/*
def sort(codes, iteration=0):
    r"""
    :param codes:
    :param iteration:
    :return:
    """
    if not (any(isinstance(code, list) for code in codes) and len(codes) > 1):
        print("EXIT")
        return codes
    for pos in reversed(range(len(codes))):
        for subpos in range(pos):
            if codes[subpos][iteration] < codes[subpos+1][iteration]: #so 0,1 becomes 1,0 -> shift 0 to rightmost
                codes[subpos], codes[subpos+1] = codes[subpos+1], codes[subpos]
    # return (grouped ones) + (grouped zeroes)
    mid = 0
    for item in codes:
        if item[iteration] == 1:
            mid += 1
    iteration += 1
    return codes
    #return sort(codes[0:mid+1], iteration) + sort(codes[mid+1:], iteration)
*/







		//Print Code
		g3.setPaint(Color.BLACK);
		String s = ("Neural Code: " + neuralCode);
		g3.drawString("Neural Code: " + neuralCode, 30, 60);
        //print out the labels of the neural codes
		for (int i = 0; i < relations; i++){
			g3.drawString("Neur. " + (i+1), 35, (y+(30*i)+7));
		}
        //algorithm to make the lines no ordering involved
        for (int i = 0; i < neuralStrings.length; i++){
			yWindow = yWindow + 30;
			lineColor = 0;
			y = 90;
			for (int j = 0; j < relations; j++){
				//System.out.print(neuralStrings[i].substring(j,j+1));
				if ((j % 8) == 0){
					lineColor = 0;
				}
				if (neuralStrings[i].substring(j,j+1).equals("1")){
					Rectangle2D rect = new Rectangle2D.Double(x, y, 50, 6);
					g3.setColor(colors.get(lineColor));
					g3.fill(rect);
					g3.setColor(Color.black);
					g3.draw(rect);
					//g3.setColor(colors.get(lineColor));
					g3.draw(new Line2D.Double(x, y, x+50, y));
					xMove = true;
				}
				y = y + 30;
				lineColor++;
			}
			if (xMove = true){
				x = x + 50;
				System.out.println("");
			}
		}

		            ImageIO.write(image, "png", new File("lineRep.jpeg"));
		            System.out.println("Panel saved as Image.");
		            //System.exit(0);
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
        }
        g3.dispose();
    }

    public static void main(String []args){
		//System.out.print("enter NeuralCode (ex: 100,110,011)\n");
		String sneuralCode = "111";
		//calls the drawing
		if(args.length > 0) {
			System.out.println("I got the arg!");
			for(String arg : args) sneuralCode = arg;
		}
        LineDisplay s=new LineDisplay(sneuralCode);
        s.paint();

        BufferedReader input = null;
    	File f = new File("lineRep.jpeg");
    	Desktop dt = Desktop.getDesktop();
    	try{
    	dt.open(f);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    		System.out.println("Done.");
    	}
}
