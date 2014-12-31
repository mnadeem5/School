#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv/cvaux.h>

int main(int argc, char** argv){
	cvNamedWindow("Example2", CV_WINDOW_AUTOSIZE);
	CvCapture* capture = cvCreateFileCapture(argv[1]);
	//CvCapture* capture = cvCaptureFromAVI(argv[1]);
	IplImage* frame;

	while(1){
		frame = cvQueryFrame(capture);
		if(!frame) break;
		cvShowImage("Example2", frame);
		char c = cvWaitKey(33);
		if (c==27)break;
	}

	//cvReleaseImage(&frame);
	cvReleaseCapture(&capture);
	cvDestroyWindow("Example2");
}