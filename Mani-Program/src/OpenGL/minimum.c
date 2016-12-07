#include <GL/glut.h>

/* 正方形のサイズを指定 */
GLdouble s = 0.2;

/* 再描写時に実行される関数*/
void display(void) {
    /* 画面全体を指定した色で塗りつぶす */
    glClear(GL_COLOR_BUFFER_BIT);

    /* 線の色を指定: 赤 */
    glColor3d(1.0, 0.0, 0.0);

    /* 描写 */
    glBegin(GL_LINE_LOOP);

    /* 図形の頂点を指定 */
    glVertex2d(-s,-s);
    glVertex2d(-s, s);
    glVertex2d( s, s);
    glVertex2d( s,-s);

    glEnd();

    /* まだ実行されていない命令をすべて実行 */
    glFlush();
}

/* 100ミリ秒ごとに実行される関数 */
void timer(int value) {
    /* 正方形のサイズを増加 */
    s += 0.01;
    /* 画面を再描写 */
    glutPostRedisplay();
    /* 100ミリ秒後に再実行 */
    glutTimerFunc(100, timer, 0);
}

int main(int argc, char **argv){
    glutInit(&argc, argv);

    /* ウィンドウの位置とサイズを指定 */
    glutInitWindowPosition(100, 100);
    glutInitWindowSize(400, 400);

    /* ウィンドウを生成 */
    glutCreateWindow("test");

    /* 背景色を指定: 白 */
    glutInitDisplayMode(GLUT_RGBA);
    glClearColor(1.0, 1.0, 1.0, 1.0);

    /* 画面を再描写するときに実行される関数を指定 
       (初期化、ウィンドウサイズ変更時など) */ 
    glutDisplayFunc(display);

    /* 100ミリ秒後に timer() を実行 */
    glutTimerFunc(100, timer, 0);

    /* ウィンドウが閉じられるまでループ */
    glutMainLoop();
    return 0;
}
