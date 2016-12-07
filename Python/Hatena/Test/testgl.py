import sys
from math import *
from OpenGL.GL import *
from OpenGL.GLU import *
from OpenGL.GLUT import *
from numpy import *

x = 0
y = 0
z = 0

def prepare_GL():
 
    glViewport(0,0,640,480)

    glClearColor(0.8,0.8,0.9,0)
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glEnable(GL_DEPTH_TEST)
    glDisable(GL_LIGHTING)
    glEnable(GL_LIGHTING)
    glEnable(GL_NORMALIZE)
    glShadeModel(GL_FLAT)

    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    gluPerspective (45,1.3333,0.2,20)

    glMatrixMode(GL_MODELVIEW)
    glLoadIdentity()

    glLightfv(GL_LIGHT0,GL_POSITION,[0,0,1,0])
    glLightfv(GL_LIGHT0,GL_DIFFUSE,[1,1,1,1])
    glLightfv(GL_LIGHT0,GL_SPECULAR,[1,1,1,1])
    glEnable(GL_LIGHT0)

    gluLookAt (2.4, 3.6, 4.8, 0.5, 0.5, 0, 0, 1, 0)


def draw_body():
    global x,y,z
    green = [0.0, 1.0, 0.0, 1.0]
    glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, green)
    R = array([1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0])
    rot = [R[0], R[3], R[6], 0.,
           R[1], R[4], R[7], 0.,
           R[2], R[5], R[8], 0.,
           x, y, z, 1.0]
    glPushMatrix()
    glMultMatrixd(rot)
    sx,sy,sz = array([1.0, 1.0, 1.0])
    glScale(sx, sy, sz)
    glutSolidCube(1)
    glPopMatrix()
def init_GL():
    glutInit ([])

    glutInitDisplayMode (GLUT_RGB | GLUT_DOUBLE)

    x = 0
    y = 0
    width = 640
    height = 480
    glutInitWindowPosition (x, y);
    glutInitWindowSize (width, height);
    glutCreateWindow ("test")

    glEnable(GL_CULL_FACE);
    glCullFace(GL_BACK);


def _keyfunc (c, x, y):
    sys.exit (0)


def _drawfunc ():
    prepare_GL()
    draw_body()

    glutSwapBuffers ()


def _idlefunc ():
    global x,y,z
    x += 0.001
    glutPostRedisplay ()

def main():
    init_GL()
    glutKeyboardFunc (_keyfunc)
    glutDisplayFunc (_drawfunc)
    glutIdleFunc (_idlefunc)

    glutMainLoop ()

if __name__ == "__main__":
    main()


