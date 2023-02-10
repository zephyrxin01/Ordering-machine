# Ordering-machine
Fast food ordering machine
## 程式碼操作說明
- 在終端機中輸入 ‵‵‵java MainMenu ‵‵‵
- 若出現錯誤，請務必確認程式碼內的索引(read and write)是否正確
- 正常執行下，首先會出現歡迎頁面，點選「開始訂購食物」，則進入點餐頁面
- 點餐頁面對應的是FoodMenu，我們透過JTabbed將主餐、附餐、飲料分成這幾頁面
	- 此頁面會因應時段提供不同食物選擇
	- 主餐的部分我們非早餐時段就用判斷式將早餐滿福堡隱藏。
	- 我們這一頁的數量增減透過Spinner來調整
	- 旁邊則是用table來呈現購物車內的物品。
	- 右下角有一個優惠碼的按鈕，點進去之後輸入java可以獲得10元折扣(單筆訂單僅能獲得一次折扣，輸入多次還是只能折抵10元)。
	- 下面的總金額也會隨著購物車金額而隨之調整。
- 如果按下確認訂購後，發現total的金額沒有數值就會用JOptionPane的showMessageDialog提示使用者尚未選擇餐點。
- 最後一個order的頁面需要使用者填寫訂購人資訊，如果三個資料有任何一個沒有填好，系統也會跳出通知提示
- 按下確認送出後系統會跳出InputDialog，可以輸入txt的檔名將購物明細印出來。按下確定後就會儲存到資料夾，再跳回歡迎頁面。

## 系統架構類別圖
- FoodbaseInfo包含了Single和Sets的共同變數和服務方法包括name,num,price,image
- Single則繼承了Foodbaseinfo 再加上id,type,breakfast 
- Sets則是再加上setid 以及運用aggregation的方式聚合了三種single型態的food.

以上用到了繼承和聚合的觀念。
- 由於我們一開始撰寫的時候其實是打算分別把主餐、副餐、飲料的物件寫出來，所以架構圖如下。
- 而這樣的寫法就會使得type沒有定義而single變成abstract class. 後續在主餐附餐和飲料各自的class中我們再來將setType實作出來，而這使用到polymorphism 的觀念。
- Utility class 的Mainmenu, Foodmenu和Ordermenu 使用到了JPanel, JFrame, JButton, JOptionPane.
這幾個套件使用到了interface和繼承的概念。
	- JFrame是繼承了Frame, implement 了WindowConstants和RootsPaneContainer和Accessible.而JButton則是繼承了AbstractButton實作了Accessible
	- JPanel則是繼承JComponent實作了Accessible
	- JOptionPane則是繼承了JComponent。

## 程式碼註解
- mainmenu
	- 功能包含顯示歡迎頁面和讀取csv 檔案並且將內容分別存取到singleList和setsList這兩種arraylist當中
	- csv的部分我們用bufferedreader來放filereader讀取完的csv 檔
	- 因為csv是用逗點間隔，所以我們第一行讀取到逗點就會存取到arrayList的下一格。
	- 最後我們將myList讀取到的內容依序儲存到變數中，再創建出一個single型態的arrayList。
- foodmenu
	- foodmenu因為需要用到以上這兩種arrayList的內容，所以我們讓foodmenu 繼承了mainmenu，而foodmenu主要功用在於展示菜單內容，並且點餐、計算總金額等等。
	- backbutton點下去後，我們會執行回到Mainmenu。
	- orderButton則是判斷是否有訂餐，如果有就會跳到下一個頁面。
	- discountbutton 就是判斷優惠的部分，若有就會在table中新增一列是折扣的內容，並且將total總額-10，把getdiscount改為true。
	- addIt，就是來顯示jtabbed不同頁面的內容。在這邊我們會用另外一個array去存取singleList的資料因為老師給的資訊並沒有將主餐附餐飲料分開，撰寫時如果想要用for迴圈會比較麻煩，所以我們索性將這些不同類別的值都先存到各類別的array
	- 那Changelistener我們就是在讀取資料內容然後更改spinner內數據。
- Order menu
	- ordermenu 則是繼承foodmenu以使用前述的arrayList和total以及是否使用優惠券等等。主要金額在填寫顧客資料以及輸出購物明細。
	- 按下確認鍵後，btnConfirm就會判定textField有無填寫資訊，若是有，就會跳出InputDialog要求輸入txt檔名，最後送出。

- 這三個utility class使用到了JPanel, JFrame, JButton, JOptionPane.
