import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
public class Planet {
    
    double x;
    double y;
    double xVelocity;
    double yVelocity;
    double mass;
    String img;
    public double xNetForce;
    public double yNetForce;
    public double xAccel;
    public double yAccel;
    public Planet(double x, double y, double xVelocity,
              double yVelocity, double mass, String img){

            this.x=x;
            this.y=y;
            this.xVelocity=xVelocity;
            this.yVelocity=yVelocity;
            this.mass=mass;
            this.img=img;
    }
    public double calcDistance(Planet p){
        double d1=p.x-this.x;
        double d2=p.y-this.y;
        return sqrt(d1*d1+d2*d2);}

    public double calcPairwiseForce(Planet p){
        
        double G=6.67e-11;
        double m1=p.mass;
        double m2=this.mass;
        return G*m1*m2/this.calcDistance(p)/this.calcDistance(p);
    }

    public double calcPairwiseForceX(Planet p) {
        double d1=p.x-this.x;
        return calcPairwiseForce(p)*d1/calcDistance(p);
        }
    public double calcPairwiseForceY(Planet p) {
        double d2=p.y-this.y;
        return calcPairwiseForce(p)*d2/calcDistance(p);
        }
    public void setNetForce(Planet [] planets){
        int index=0;
        xNetForce=0;
        yNetForce=0;
        while (index< planets.length){
            if (this == planets[index]) {
            } else {
                xNetForce = xNetForce + this.calcPairwiseForceX(planets[index]);
                yNetForce = yNetForce + this.calcPairwiseForceY(planets[index]);
            }
            index = index + 1;
        }
    }
    public  void draw (){
        StdDraw.picture (this.x,this.y,"images/" + img);

    }
    public void update (double dt){
        xAccel=xNetForce/this.mass;
        yAccel=yNetForce/this.mass;
        xVelocity=xVelocity+dt*xAccel;
        yVelocity=yVelocity+yAccel*dt;
        x=x+dt*xVelocity;
        y=y+dt*yVelocity;


    }

}

