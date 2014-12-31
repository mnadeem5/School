#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv/cvaux.h>

void example( IplImage* image )
{
    // Create some windows to show the input
    // and output images in.
    //
    cvNamedWindow( "Example-in", CV_WINDOW_AUTOSIZE );
    cvNamedWindow( "Example-out", CV_WINDOW_AUTOSIZE );
    
    // Create a window to show our input image
    //
    cvShowImage( "Example-in", image );
    
    // Create an image to hold the smoothed output
    //
    IplImage* out = cvCreateImage(
                                  cvGetSize(image),
                                  IPL_DEPTH_8U,
                                  3
                                  );
    
    // Do the smoothing
    //
    cvSmooth( image, out, CV_GAUSSIAN, 15,15 );
    
    // Show the smoothed image in the output window
    //
    cvShowImage( "Example-out", out );
    
    // Be tidy
    //
    cvReleaseImage( &out );
    
    // Wait for the user to hit a key, then clean up the windows
    //
    cvWaitKey( 0 );
    cvDestroyWindow("Example-in" );
    cvDestroyWindow("Example-out" );
    
}

int main( int argc, char** argv )
{
    IplImage* img = cvLoadImage( argv[1] );
    example( img );
    cvReleaseImage( &img );
    cvDestroyWindow("Example1");
}

