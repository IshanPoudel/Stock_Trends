<img src = "https://github.com/IshanPoudel/InvestmentResearch/blob/main/Assets/StockTrend.png" width = "75" height = "70" alt = "Logo">

# Stock_Trends

Stock market analyzer using News headlines and sumary by utilizing Natural Language Processing and Sentiment analysis. Developed custom Machine Learning model for stock news sentiment. 

<h1><b>About</b></h1> 

Stock Trends is an android application . It is suppoted in the backend by a database. 
The application communicates to the backend via microservices. 


The backend for the application was developed using Python and Flask. 
A crawler written using beautifulSoup and Selenium crawls through various financial websites to collect news from various websites. 
The news headline and summary is stored in a MySQL database. 



The sentiment analyzer model is written by tokenizing the News headline and the news summary by using the FinBERT model (Financial Bidirectional Encoder Representations from Transformers) . 
The model is deployed in a flask server. 


Each time the application works makes a call to the flask server to get the stock sentiment which is displayed in the application.


<h1><b>Requirements</b></h1> 

- <b>Python 3.x </b>

- Seleniumn python module
- nltk python module
- requests python module
- beautifulsoup4 python module
- textblob python module
- flask python module

- <b>Android Studio</b>

<h1><b> How to Download and make further changes in a local instance </b></h1>

<b> Download the Python package for the backend. 

$ git clone https://github.com/IshanPoudel/InvestmentResearch.git
Run server.py

$git clone https://github.com/IshanPoudel/Stock_Trends.git
Run Mainactivity.java


<h1><b> How to run the webcrawler </b></h1>
~ Input in the database host , username and password at config.json
~ Run GetNews.py

If a crawler gets news for a company , it outputs the following : Added news from stock_ticker

<img src="https://github.com/IshanPoudel/InvestmentResearch/blob/main/Assets/TryCatch_AddedValue.png" alt="Added News" />

If a crawler fails to gets news from a company , it outputs the following : Could not get news from stock_ticker

<img src="https://github.com/IshanPoudel/InvestmentResearch/blob/main/Assets/TryCatch_NoValue.png" alt="No News" />


<h1> Front Activtiy Demonstration </h1>



![Alt Text](https://github.com/IshanPoudel/InvestmentResearch/blob/main/Assets/demo.gif)



<h1> Specific Stock Activity </h1>

<img src = "https://github.com/IshanPoudel/InvestmentResearch/blob/main/Assets/MSFT_Prices.png" width = "300" height = "600" >

<img src = "https://github.com/IshanPoudel/InvestmentResearch/blob/main/Assets/XOM_House_Prices.png" width = "300" height = "600">



