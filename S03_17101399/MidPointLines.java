import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 *
 * @author sajib
 */
public class MidPointLines  implements GLEventListener {
    /**
     * Interface to the GLU library.
     */
    private GLU glu;

    /**
     * Take care of initialization here.
     * @param gld
     */
    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    /**
     * Take care of drawing here.
     * @param drawable
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        /*
         * put your code here
         */
        //points should be in the same zone
        DrawMPL(gl,10,10,60,50);
        DrawMPL(gl,10,-10,60,-50);
        DrawMPL(gl,-30,-10,-100,-40);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }

    int dx, dy, b;
    private void DrawMPL(GL2 gl, int x1, int y1, int x2, int y2) {
        //write your own code
        mid_point_eight(gl, x1, y1, x2, y2);

    }

    //y=mx+b
    private int func(int x, float y){
        return (int)(dy*x - y*dx + b*dx);
    }


    int findZone(int x1, int y1, int x2, int y2) {
        int zone = 0;
        return zone ;
    }

    int convertX(int x, int y, int zone){
        int convertedX=0;
        return convertedX;
    }

    int convertY(int x, int y, int zone){
        int convertedY=0;
        return convertedY;
    }


    public void dispose(GLAutoDrawable arg0) {
        //do nothing
    }
    public static void mid_point_eight(GL2 gl, int x1, int y1, int x2, int y2){
        int dx=x2-x1;
        int dy=y2-y1;
        int real_inc_E=2*dy;
        int real_inc_NE=2*(dy-dx);
        System.out.println("inc_E: "+real_inc_E+"  inc_NE: "+real_inc_NE);
        int[] a= new int[2];
        a[0]=x1;
        a[1]=y1;
        int[] b=new int[2];
        b[0]=x2;
        b[1]=y2;
        int flag=0;
        if(dx>0 && dy>0 && Math.abs(dx)<Math.abs(dy)){
            conv1(a,b);
            flag=1;
            System.out.println("Zone 1");
        }
        else if(dx<0 && dy>0 && Math.abs(dx)<Math.abs(dy)){
            conv2(a,b);
            flag=2;
            System.out.println("Zone 2");
        }
        else if(dx<0 && dy>0 && Math.abs(dx)>Math.abs(dy)){
            conv3(a,b);
            flag=3;
            System.out.println("Zone 3");
        }
        else if(dx<0 && dy<0 && Math.abs(dx)>Math.abs(dy)){
            conv4(a,b);
            flag=4;
            System.out.println("Zone 4");
        }
        else if(dx<0 && dy<0 && Math.abs(dx)<Math.abs(dy)){
            conv5(a,b);
            flag=5;
            System.out.println("Zone 5");
        }
        else if(dx>0 && dy<0 && Math.abs(dx)<Math.abs(dy)){
            conv6(a,b);
            flag=6;
            System.out.println("Zone 6");
        }
        else if(dx>0 && dy<0 && Math.abs(dx)>Math.abs(dy)){
            conv7(a,b);
            flag=7;
            System.out.println("Zone 7");
        }

        int new_dx=b[0]-a[0];
        int new_dy=b[1]-a[1];
        int d= 2*new_dy-new_dx;
        int inc_E=2*new_dy;
        int inc_NE=2*(new_dy-new_dx);
        System.out.println("inc_E': "+inc_E+"  inc_NE': "+inc_NE);
        System.out.println("__________________________________");
        System.out.println("x': "+a[0]+" y': "+a[1]+"    d': "+d);
        System.out.println("x: "+x1+" y: "+y1+"    d: "+d);
        System.out.println("__________________________________");
        while(a[0]<b[0]){
            if(d<=0){
                d=d+inc_E;
                a[0]=a[0]+1;
            }
            else{
                d=d+inc_NE;
                a[0]=a[0]+1;
                a[1]=a[1]+1;
            }
            System.out.println("x': "+a[0]+" y': "+a[1]+"    d: "+d);
            int[] x=new int[2];
            x[0]=a[0];
            x[1]=a[1];
            if(flag==1){
                print1(gl, x);
            }else if(flag==2){
                print2(gl, x);
            }else if(flag==3){
                print3(gl, x);
            }
            else if(flag==4){
                print4(gl, x);
            }
            else if(flag==5){
                print5(gl, x);
            }
            else if(flag==6){
                print6(gl, x);
            }else if(flag==7){
                print7(gl, x);
            }
            else{
                print(gl, x);
            }
        }
    }
    public static void conv1(int[] a, int[] b){
        int temp=a[0];
        a[0]=a[1];
        a[1]=temp;

        temp=b[0];
        b[0]=b[1];
        b[1]=temp;
    }
    public static void conv2(int[] a, int[] b){
        int temp=a[0]*(-1);
        a[0]=a[1];
        a[1]=temp;

        temp=b[0]*(-1);
        b[0]=b[1];
        b[1]=temp;
    }
    public static void conv3(int[] a, int[] b){
        a[0]=a[0]*(-1);

        b[0]=b[0]*(-1);
    }
    public static void conv4(int[] a, int[] b){
        a[0]=a[0]*(-1);
        a[1]=a[1]*(-1);

        b[0]=b[0]*(-1);
        b[1]=b[1]*(-1);
    }
    public static void conv5(int[] a, int[] b){
        a[0]=a[0]*(-1);
        a[1]=a[1]*(-1);
        int temp=a[0];
        a[0]=a[1];
        a[1]=temp;

        b[0]=b[0]*(-1);
        b[1]=b[1]*(-1);
        temp=b[0];
        b[0]=b[1];
        b[1]=temp;
    }
    public static void conv6(int[] a, int[] b){
        a[1]=a[1]*(-1);
        int temp=a[0];
        a[0]=a[1];
        a[1]=temp;

        b[1]=b[1]*(-1);
        temp=b[0];
        b[0]=b[1];
        b[1]=temp;
    }
    public static void conv7(int[] a, int[] b){
        a[1]=a[1]*(-1);

        b[1]=b[1]*(-1);
    }


    public static void print1(GL2 gl, int[] a){
        int temp=a[0];
        a[0]=a[1];
        a[1]=temp;
        print(gl, a);
    }
    public static void print2(GL2 gl, int[] a){
        int temp=a[0];
        a[0]=a[1]*(-1);
        a[1]=temp;
        print(gl, a);
    }
    public static void print3(GL2 gl, int[] a){
        a[0]=a[0]*(-1);
        print(gl, a);
    }
    public static void print4(GL2 gl, int[] a){
        a[0]=a[0]*(-1);
        a[1]=a[1]*(-1);
        print(gl, a);
    }
    public static void print5(GL2 gl, int[] a){
        a[0]=a[0]*(-1);
        a[1]=a[1]*(-1);
        int temp=a[0];
        a[0]=a[1];
        a[1]=temp;
        print(gl, a);
    }
    public static void print6(GL2 gl, int[] a){
        a[0]=a[0]*(-1);
        int temp=a[0];
        a[0]=a[1];
        a[1]=temp;
        print(gl, a);
    }
    public static void print7(GL2 gl, int[] a){
        a[1]=a[1]*(-1);
        print(gl, a);
    }
    public static void print(GL2 gl, int[] a){
        System.out.println("x: "+a[0]+"    y: "+a[1]);
        System.out.println("__________________________________");
        gl.glPointSize(1.0f);
        gl.glColor3d(1, 0, 0);

        gl.glBegin(GL2.GL_POINTS);


        gl.glVertex2d(a[0], a[1]);
        //gl.glVertex2d(x2, y2);

        gl.glEnd();
    }
}

