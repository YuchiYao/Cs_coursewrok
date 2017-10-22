  public class Planet {

    double xxPos,yyPos,xxVel,yyVel,mass;
    String imgFileName;
    public Planet (double xP, double yP, double xV, double yV, double m, String img){
        xxPos =xP;
        yyPos =yP;
        xxVel =xV;
        yyVel =yV;
        mass  = m;
        imgFileName=img;
    }
    public Planet (Planet p){
        this.xxPos =p.xxPos;
        this.yyPos =p.yyPos;
        this.xxVel=p.xxVel ;
        this.yyVel=p.yyVel;
        this.mass =p.mass;
        this.imgFileName=p.imgFileName;
    }
    
    double calcDistance(Planet p){
        double x_distacne =p.xxPos - xxPos;
        double y_distacne =p.yyPos - yyPos;
        return Math.sqrt(Math.pow(x_distacne,2)+Math.pow(y_distacne,2));
    }
    
    double calcForceExertedBy(Planet p){
        double G = 6.67*Math.pow(10,-11);
        return G*p.mass*mass/Math.pow(calcDistance(p),2);
    }
    
    double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    }
    
    double calcForceExertedByY(Planet p){
       return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    }
    
    double calcNetForceExertedByX(Planet[] planets ){
        double NeTforce=0;
        for(int i=0;i<planets.length;i++){
            if (planets[i].equals(this)!=true){
                NeTforce +=calcForceExertedByX(planets[i]);
            }
        }
        return NeTforce;
    }
    
    double calcNetForceExertedByY(Planet[] planets){
        double NeTforce=0;
        for(int i=0;i<planets.length;i++){
            if (planets[i].equals(this)!=true){
                NeTforce +=calcForceExertedByY(planets[i]);
            }
        }
        return NeTforce;
    }
    void update(double time,double x_force,double y_force){
        double a_x= x_force/mass;
        double a_y= y_force/mass;
        xxVel = a_x * time+xxVel;
        yyVel = a_y * time+yyVel;
        xxPos = xxVel*time+xxPos;
        yyPos = yyVel*time+yyPos;
    }
      void draw(){
          StdDraw.picture(xxPos, yyPos,"images/"+imgFileName);
      }

    public static void main(String[] args) {
        
    }
    
}
