#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv/cvaux.h>

int main(int argc, char** argv){
	IplImage* in = cvLoadImage(argv[1]);

	cvNamedWindow("Eaxmple3In", CV_WINDOW_AUTOSIZE);
	cvNamedWindow("Eaxmple3Out", CV_WINDOW_AUTOSIZE);

	cvShowImage("Eaxmple3In", in);

	IplImage* out = cvCreateImage(cvGetSize(in), IPL_DEPTH_8U, 3);

	cvSmooth(in, out, CV_GAUSSIAN, 21, 21);
	cvShowImage("Eaxmple3Out", out);

	cvReleaseImage(&in);
	cvReleaseImage(&out);

	cvWaitKey(0);
	cvDestroyWindow("Eaxmple3In");
	cvDestroyWindow("Eaxmple3Out");
}
