public class NBody {
	public static void main (String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		In in = new In(filename);
		int numOfPlanet = in.readInt();
		double sizeOfUniverse = in.readDouble();
		Planet[] planets = new Planet[numOfPlanet];
		for (int i = 0; i < numOfPlanet; i += 1) {
			planets[i] = getPlanet(in);
		}
		StdDraw.setScale(-sizeOfUniverse, sizeOfUniverse);
		int time = 0;
		while (time < T) {
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet i : planets) {
				i.xNetForce = 0;
				i.yNetForce = 0;
				i.setNetForce(planets);
			}
			for (Planet i : planets){
				i.update(dt);
				i.draw();
			}
			StdDraw.show(10);
			time += dt;
		}
		StdOut.printf("%d\n", numOfPlanet);
		StdOut.printf("%.2e\n", sizeOfUniverse);
		for (int Pla = 0; Pla < numOfPlanet; Pla++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",planets[Pla].x, planets[Pla].y, planets[Pla].xVelocity, planets[Pla].yVelocity, planets[Pla].mass, planets[Pla].img);
		}
	} 
	public static Planet getPlanet(In in) {
		double x = in.readDouble();
		double y = in.readDouble();
		double xVelocity = in.readDouble();
		double yVelocity = in.readDouble();
		double mass = in.readDouble();
		String img = in.readString();
		Planet new_Planet = new Planet(x,y,xVelocity,yVelocity,mass,img)	;
		return new_Planet;
	}	
}
	