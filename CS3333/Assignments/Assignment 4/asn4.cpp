/**********************************************************************
asn4.cpp
Name: Zaid Albirawi
id: zalbiraw
#: 250626065
date: Nov 18, 2014
**********************************************************************/

#include <stdio.h>
#include <fstream>
#include <iostream>
#include "opencv2/core/core.hpp"
#include <opencv2/opencv.hpp>
#include "opencv2/features2d/features2d.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/calib3d/calib3d.hpp"
#include "opencv2/nonfree/nonfree.hpp"
#include "opencv2/imgproc/imgproc.hpp"

#define MIN_HESSIAN 400
#define MIN_DES 100
#define GOOD_MATCH 3

using namespace cv;
using namespace std;

/******************************************************************************
* find_descriptors, finds the images features in the provided images
*
* Input:
*		image_1: 			Mat contains the first image
* 		image_2: 			Mat contains the second image
*		key_pt_1: 			vector<KeyPoint> will be populated with a list of
*							the key points in image 1
* 		key_pt_2: 			vector<KeyPoint> will be populated with a list of
*							the key points in image 2
* 		image_descriptor_1: Mat will be populated with the image 1 discriptros
* 		image_descriptor_2: Mat will be populated with the image 2 discriptros
******************************************************************************/
void find_descriptors(Mat image_1, Mat image_2, vector<KeyPoint> *key_pt_1
	, vector<KeyPoint> *key_pt_2, Mat *image_descriptor_1
	, Mat *image_descriptor_2)
{
	int min_hessian = MIN_HESSIAN;
	SurfFeatureDetector detector(min_hessian);
	SurfDescriptorExtractor extractor;

	/**************************************************************************
	* populates the key_pt_1 and key_pt_2 vectors with possible key points 
	**************************************************************************/
	detector.detect(image_1, *key_pt_1);
	detector.detect(image_2, *key_pt_2);

	/**************************************************************************
	* populates the image_descriptor_1 and image_descriptor_2 Mats with image
	* descriptors
	**************************************************************************/
	extractor.compute(image_1, *key_pt_1, *image_descriptor_1);
	extractor.compute(image_2, *key_pt_2, *image_descriptor_2);
}

/******************************************************************************
* find_matches, finds the good matches between the provieded images, a good 
* match defined by a distance that is n * minimum distance
*
* Input:
*		matches_final:		vector<DMatch> will be populated with the good
*							matches
* 		image_descriptor_1: Mat will be populated with the image 1 discriptros
* 		image_descriptor_2: Mat will be populated with the image 2 discriptros
******************************************************************************/
void find_matches(vector<DMatch> *matches_final, Mat image_descriptor_1
	, Mat image_descriptor_2)
{
	FlannBasedMatcher flann_matcher;
	vector<DMatch> matches;
	int i;
	double dis, min_dis = MIN_DES;

	/**************************************************************************
	* populates the matches vector
	**************************************************************************/
	flann_matcher.match(image_descriptor_1, image_descriptor_2, matches);

	/**************************************************************************
	* finds the min distance for matches 
	**************************************************************************/
	for(i = 0; i < image_descriptor_1.rows; i++)
		if((dis = matches[i].distance) < min_dis) 
			min_dis = dis;

	/**************************************************************************
	* finds the final/good matches
	**************************************************************************/
  	for(i = 0; i < image_descriptor_1.rows; i++)
  		if(matches[i].distance < GOOD_MATCH*min_dis)
  		 	matches_final->push_back(matches[i]); 
}

/******************************************************************************
* find_homography, finds the homography between the images provided 
*
* Input:
*		key_pt_1: 			vector<KeyPoint> will hold a list of the key points
*							in image 1
* 		key_pt_2: 			vector<KeyPoint> will hold a list of the key points
*							in image 2
*		matches_final:		vector<DMatch> will hold a list of the good matches
******************************************************************************/
Mat find_homography(vector<KeyPoint> key_pt_1, vector<KeyPoint> key_pt_2
	, vector<DMatch> matches_final)
{
	int i;
	vector<Point2f> image_1, image_2;

	/**************************************************************************
	* generates the image vectors based on the matches
	**************************************************************************/
  	for(i = 0; i < matches_final.size(); i++)
  	{
    	image_1.push_back(key_pt_1[matches_final[i].queryIdx].pt);
    	image_2.push_back(key_pt_2[matches_final[i].trainIdx].pt);
 	}
  	
	/**************************************************************************
	* returns the homography Mat
	**************************************************************************/
  	return findHomography(image_1, image_2, CV_RANSAC);
}

/******************************************************************************
* stitch, stitches the images based on the homography result
* Input:
*		image_1: 			Mat contains the first image
* 		image_2: 			Mat contains the second image
******************************************************************************/
Mat stitch(Mat image_1, Mat image_2)
{
	vector<KeyPoint> key_pt_1, key_pt_2;
	Mat image_descriptor_1, image_descriptor_2, H, warp;
  	vector<DMatch> matches_final;

	/**************************************************************************
	* finds the discriptors between the two images
	**************************************************************************/
	find_descriptors(image_1, image_2, &key_pt_1, &key_pt_2
		, &image_descriptor_1, &image_descriptor_2);
	/**************************************************************************
	* finds the matches based on the discriptors prvided by find_descriptors
	**************************************************************************/
	find_matches(&matches_final, image_descriptor_1, image_descriptor_2);
	/**************************************************************************
	* finds the homography matrix
	**************************************************************************/
 	H = find_homography(key_pt_1, key_pt_2, matches_final);

 	/**************************************************************************
	* generates the warpped image based on the homography matrix
	**************************************************************************/
  	warpPerspective(image_1, warp, H, Size(image_2.cols, image_2.rows));
	image_2.copyTo(warp, image_2);

  	return warp;
}

/******************************************************************************
* takes in the images stitches them and writes the result.
******************************************************************************/
int main(int argc, char** argv)
{
	int i, num_images = argc - 1;
	Mat images[num_images];

	/**************************************************************************
	* reads in the images
	**************************************************************************/
	for(i = 0; i < num_images; i++)
		images[i] = imread(argv[i+1], CV_LOAD_IMAGE_GRAYSCALE);

	/**************************************************************************
	* Creates the result image and copies the first image to it
	**************************************************************************/
	Mat result(images[0].rows*2,images[0].cols*2,images[0].type());
	images[0].copyTo(result(Rect(images[0].cols/2, images[0].rows/2
		, images[0].cols, images[0].rows)));
	/**************************************************************************
	* stitches images starting with the result(contains the first image) and
	* the second image
	**************************************************************************/
	for(i = 1; i < num_images; i++)
		result = stitch(images[i], result);

	imwrite("Result.jpg",result);
}