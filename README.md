# ModernRecyclerViewMVP
RxJava2+MVP+Retrofit+Volley+Dagger2+Awesome RecyclerView+DependencyInjection+Butterknife<br>
<br>
This is a mdern day Android app.Contains all Hot and latest topics of Android.What does this app Contains?<br>
<b>1.Rxjava2+Retrofit Data Loading in Recyclerview using MVP architecture in a more simpler way</b><br>
<b>2.Dependency Injection implementation using Dagger2 in a more simpler way</b><br>
<b>3.Awesome Recyclerviews with Mvp architecture</b><br>
<b>4.Data Binding using Butterknife</b><br>
<b>Drawer Functionality with MVP architecture</b><br>

MainActivity.java will call navigationItemSelected() of DrawerPresenterImpl
DrawerPresenterImpl will implement methods of DrawerPresenter called navigationItemSelected()
navigationItemSelected() will contain method navigateTo() that will call when some items from drawer will selected
navigateTo() is the implemetation of method present in DrawerIntercator interface.
fragments will be replaced from fragmentreplace() in DrawerListener interface.

<b>Rxjava=</b>In most Android applications, you are reacting to user interactions (clicks, swipes and etc.) while doing something else in the background (networking).

Orchestrating all of this is a hard thing and could quickly turn into an unmanageable code mess.

For example, it isn't trivial to send a request to a database over network and after it completes start fetching user messages and preferences at the same time, and after all of that is complete show a welcome message.

This is a case where RxJava (ReactiveX) excels - orchestrating multiple actions that happen due to certain events in the system.<br>
<b>I am sharing some screenshots of the app</b>
<br>
<img src="https://github.com/vikashumain/ModernRecyclerViewMVP/blob/master/Screenshot_2018-01-30-17-18-29-638_com.cogoport.png"><br>
<img src="https://github.com/vikashumain/ModernRecyclerViewMVP/blob/master/Screenshot_2018-01-30-17-18-57-007_com.cogoport.png"><br>
<img src="https://github.com/vikashumain/ModernRecyclerViewMVP/blob/master/Screenshot_2018-01-30-17-19-28-568_com.cogoport.png"><br>
<img src="https://github.com/vikashumain/ModernRecyclerViewMVP/blob/master/Screenshot_2018-01-30-17-19-38-348_com.cogoport.png"><br>

