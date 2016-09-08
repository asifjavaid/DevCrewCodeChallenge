

1. What is the major difference between an abstract class and an interface?

ANS.
The methods of a interface are abstract and cannot have any body or implementations. But an abstract class can have instance methods that implements a default behaviour.
Interface is basically a contract that a class has to implement its all methods. But a class extending from an abstract class has an option to whether override methods or use predefined behaviour.

2. Why is Java 7’s class inheritance flawed?

ANS.
Not understand this question completely. Are you people talking about Diamond problem in multiple inheritance?

3. What are the major differences between Activities and Fragments?

ANS.
Activities are one of the core application component of Android with which user can interact and can perform the actions available on it. 
Fragment is just like a custom control or a part of user interface, which will be used by an activity. More likely we create fragment of common infomration and place it on all screens to make code reusable, clean and manageable. For example, header fragment, footer fragment, dialog fragments.


4. When using Fragments, how do you communicate back to their hosting Activity?

ANS.
Using interfaces. We will define an interface in the fragment, and activity has to implement that interface to get any responses from fragment. In code challange, I have implemented AddContactDialogFragment, you can see the code, how it is working with activity.

5. Can you make an entire app without ever using Fragments? Why or why not?

ANS.
Yes we can create an entire app without ever using Fragments as I did my first Android application :), but it will not be manageable, and too much extra code and its repeation. Using fragments, we will write their code once but we can  use them as needed e.g make common controls and dialogs fragments.

Are there any special cases when you absolutely have to use or should use Fragments?

ANS.
a. Dialog fragment is one of them. 
b. Sliding Drawer for Left Or right menu.
c. Full Drawer siding window from left to right or right to left.
d. Multiple views like, portrait and landscape views and portrait detail view (parent and child/detail view), can be acheived by implementing two fragments, ListFragment and DetailFragment.
e. And many more.

6. What makes an AsyncTask such an annoyance to Android developers? Detailsome of the issues with AsyncTask, and how to potentially solve them.

ANS.

AsyncTask’s primary goal is to make it easy to run a Thread in the background that can later interact with the UI thread. Therefore the most common use case is to have an AsyncTask run a time-consuming operation that updates a portion of the UI when it’s completed in AsyncTask.onPostExecute(). This works fine until you rotate the screen. When an app is rotated, the entire Activity is destroyed and recreated. When the Activity is restarted, your AsyncTask’s reference to the Activity is invalid, so onPostExecute() will have no effect.

The solutions I have are:

1. In onCreate(Bundle savedInstanceState) method, check "savedInstanceState" variable, if it is NULL then activity is created first time, but if it is not NULL then it already created, and the we have to avoid the timer consuming operation to execute again using AsyncTask.

2. We can also use loadermanager. Which manages all these states automatically. 












