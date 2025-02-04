# Used Car Price Prediction App

## Model Update
This project uses the RandomForest model to predict used car prices.
The model was exported using RandomForest 1.5.2, but the current version in use is 1.6.
If you encounter compatibility issues, you can rerun the cells in Jupyter Notebook or your Python environment to re-export the model in pickle format.

## Flask API Connection
After starting the Flask API, you need to replace the generated "http://10.xx" address in the following files:

Line 92 in the PredictionActivity class

Line 12 in the ApiClient class

After making these changes, your application will communicate with your own API.

## Application Interface
The application provides a user-friendly interface for predicting car prices. The prediction section requires the following details:

Brand: Select your car's brand.

Model: Enter your car's model.

Mileage: Enter the current mileage of your car (in miles).

Engine Capacity: For example, enter 4.0 for a 4.0 V8 engine.

Fuel Type: Select the fuel type, such as gasoline, diesel, or electric.

Transmission Type: Choose between manual or automatic transmission.

After entering all the details, press the "Predict Price" button to see the estimated value of your car.

