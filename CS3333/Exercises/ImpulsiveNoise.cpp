#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv/cvaux.h>

void addImpulsiveNoise(IplImage* image, int n) {
    
    CvScalar rgba ;
    
    rgba.val[0] = 255 ;
    rgba.val[1] = 255 ;
    rgba.val[2] = 255 ;
    rgba.val[3] = 0 ;
    
    for (int k = 0 ; k < n; k++) {
        int i = rand()%(*image).width ;
        int j = rand()%(*image).height ;
        cvSet2D(image, i, j, rgba) ;
  }
}


void impulsiveNoiseExample(IplImage* image) {

    cvNamedWindow("Original Image") ;
    cvShowImage( "Original Image",image) ;
    
    addImpulsiveNoise(image, 1024) ;
    
    cvNamedWindow("Impulsive Noise Image") ;
    cvShowImage("Impulsive Noise Image",image) ;
    
    cvWaitKey(0) ;
    
    cvDestroyWindow("Original Image") ;
    cvDestroyWindow("Impulsive Noise Image") ;
    
}

int main( int argc, char** argv )
{
    IplImage* img = cvLoadImage(argv[1]) ;
    
    impulsiveNoiseExample(img) ;
    cvReleaseImage(&img) ;
}