# KNN

This project was realized during the **Artificial Intelligence Tools** course at the Polish-Japanese Academy of Information Technology.

Project implements a k-Nearest Neighbors (kNN) classifier from scratch, using the Iris dataset.

## Key Features

### Data Handling
- The program reads the Iris dataset from a text file.

### Algorithm Overview
- The algorithm calculates the **Euclidean distance** between the vector being classified and all training vectors.
- It classifies the test vector based on the **mode** (most frequent class) of the `k` nearest training vectors.

### Accuracy Calculation
- The program computes the **accuracy** of the model by outputting the percentage of correctly classified test vectors.

## Interfaces

### Manual Testing
- The program provides an interface for users to manually input test vectors.
- Based on the input, the program will classify the vector and return the predicted class.

### File-based Interface
- Users can also provide a file containing test vectors.
- The program will classify each vector in the file and return the percentage of correctly classified vectors.

## Outputs
- For each test vector, the program outputs the predicted class based on the k-nearest neighbors.
- It also returns the overall accuracy of the classifier on the provided test data.
