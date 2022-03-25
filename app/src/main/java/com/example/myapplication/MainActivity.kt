@file:JvmName("MyKotlinFileName")
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getKotlinPractice();
    }

    fun getKotlinPractice(){

        //*************************** FOR LOOP*******************************************************//
        Log.e("**","***************** For Loop in Range **************" )
        for (i in 1..10 step 3) {
            Log.e("i:-",""+i )
        }

        //Iterate the for loop via rang using DownTo function and get the value of every 2 step
        for(i in 10 downTo 1 step 2 ){
            Log.e("DowntoNumber:-",""+i);
        }


        //Iterate the for loop via Array without index
        Log.e("**","***************** For Loop Without Index **************" )

        var number= arrayOf(1,2,3,4,5,6,7,8,9,10)
        for(num in number){
            Log.e("Number:-",""+num)
        }

        //Iterate the for loop via Array with indices
        var names= arrayOf("Mohit","Lucky","Hitesh")
        for(i in names.indices){
            Log.e("Names:-",""+names[i])
        }

        //Iterate the for loop via Array withIndex function
        for((index,value ) in names.withIndex()){
            Log.e("NamesWithIndexValue:-",""+value)
        }

        print("Enter text: ")
        var input = readLine()
        print("You entered: $input")


        //********************************* WHEN LOOP LIKE SWITCH **********************************//

        Log.e("****:","************ When as Expression ***********\n\n\n")
        var a=2
        //as a expression
        var b=when(a){
             2 -> "Mohit"
            else -> {
                "Unkonown"
                Log.e("When loop:","The value is unknown")
            }
        }
        Log.e("The value of b is:-",""+b)


        //When as a statment . Mutliple values in a single Branch shared same logic
        Log.e("**","********* When Without Expression ************")
        Log.e("**","********* When Using Multiple Value check in Single Branch ************\n\n\n")
        var c=5
        when(c)
        {
            2,3,4,5,6 -> Log.e("When Statement:-","${c} is present in given branch")
            else->{
                Log.e("Else:-","${c} is not present in given branch")
            }
        }
        //The value lies in a range example
        Log.e("**","********* When Using Range (in) Operator ************\n\n\n")
        var k:Int=3
        when(k){
            in 2..5->{
                Log.e("Existence of Value","True")
            }else->{
            Log.e("Existence of Value","False")
        }
        }

        //Using is operator
        Log.e("**","********* When Using is Operator ************\n\n\n")
        var num: Any = "GeeksforGeeks"
        when(num){
            is Int -> Log.e("Integer:","It is an Integer")
            is String -> Log.e("String:","It is a String")
            is Double -> Log.e("Double","It is a Double")
        }

        /////************ *******************BREAK STATEMENTS****************************************///
        Log.e("**","************ Break Statements ****************\n\n\n")
        myloop@ for(i in 1..3){
            for(j in 1..3){
                Log.e("Value:-",""+i+ " "+j)

                if(i==2 && j==2){
                    break@myloop
                }
            }
        }

        // ********************************* FUNCTION *************************************************//

        // function as Expression
        Log.e("**","************ Function As Expression ****************\n\n\n")
        Log.e("The greater value is",""+max(5,2))

        //***********************************INTEROPERABILITY ****************************************//
        Log.e("**","************ InterOperability ****************\n\n\n")
        Log.e("Addition of a & b is:",""+AddActivity.Add(5,5))

        //***************************** DEFAULT FUNCTION *********************************************//
        Log.e("**","************ Default Function ****************\n\n\n")
        Log.e("Result of Volume:",""+findVolume(10,20))


        //****************************NAMED PARAMETER ************************************************//
        Log.e("**","************ Named Parameter Function ****************\n\n\n")
        namedFunction(breadth = 3,length=2,height = 20)


        //******************* EXTENSION FUNCTION *****************************************************//
        Log.e("**","************** Extension Function *************\n\n\n\n")
        var xy="Hello Mohit How are you"
        var message=Message() //create object of Message
        Log.e("small message:",""+message.showMessage(xy)) //call function inside the Message

        var abc="You have to join the office soon "
        //call extension function inside the Message(User define Class)
        Log.e("Office Message:-",""+message.addMessage1(abc))

        var x : String = "Hey Mohit, "
        var y : String= "Your salary has been transferred "
        //call Extension Function inside String Class (Predefined Class)
        Log.e("AddMessage is:-",""+x.addMessage(y))

        //*****************INFIX FUNCTION*************************//

        Log.e("**","************** Infix Funtion *************\n\n\n")
        //All Infix fuction is a Extension fuction but
        //All Extension function are not Infix function
        //Infix function contains only one parameter
        var x1 : String = "Hey Mohit, "
        var y1 : String= "Your Laptop has been ready please collect from the laptop"
        Log.e("Infix message is:-",""+x1 addMessage y1)

        //********** CLASS WITH PROPERTY ,INIT, PRIMARY CONSTRUCTOR  WITH SECONDARY CONSTRUCTOR ******//

        Log.e("**","************** PRIMARY CONSTRUCTOR WITH SECONDARY CONSTRUCTOR *************\n\n\n")
        var student=Student("Hello Mohit",2)
        Log.e("String is:-",""+student.name)


        //*********** Inheritance Class in Kotlin **************************//

        Log.e("**","************** INHERITANCE CLASS IN KOTLIN *************\n\n\n")
        var dog=Dog()
        dog.breed="breading"
        dog.Barking()
        dog.eat()
        Log.e("Dog color is :",""+dog.color)

        var cat=Cat()
        cat.age=4
        cat.color="white"
        cat.Meow()
        cat.eat()

        //***************** INHERITANCE WITH PRIMARY  AND SECONDARY CONSTRUCTOR **********************//
        Log.e("**","************** INHERITANCE WITH PRIMARY  AND SECONDARY CONSTRUCTOR *************\n\n\n")
        //example 1
        var dog1=Dog1("black")

        //example 2
        var brother=Brother("Mathematics",20)

        //example 3
        var dog2=  Dog2("BOW BOW","RED")

        //***********************ABSTRACT CLASS IN KOTLIN ***********************************************//

        Log.e("**","************** ABSTRACT CLASS EXAMPLE IN KOTLIN *************\n\n\n")
        var educ=Eduction()
        educ.Teacher()
        educ.Student()

        //*********************************** INTERFACE IN KOTLIN ************************************//
         Log.e("**","************** INTERFACE IN KOTLIN *************\n\n\n\n\n")
        var classb= ClassB()
        classb.A()
        classb.B()

        //********************************** DATA CLASS AND COPY METHOD ********************************//
        Log.e("**","************** Data class and copy method *************")
        Log.e("**","************** Class using data keyoword *************\n")
         var students=Students("Mohit","23")
         var students1=Students("Mohit","23")

        //Without using data keyword it compares the reference of object
        //With using data keyword it compares the values inside the object
        if(students==students1) Log.e("equal","True") else Log.e("not equal","True")

        Log.e("**","************** Class Without using data keyoword *************\n")
        var j1= Juniors("Mohan", "2")
        var j2= Juniors("Mohan", "2")

        Log.e("j1:-","${j1}")
        Log.e("j2:-","${j2}")
        if(j1==j2) Log.e("Condition is equal","True") else Log.e("Condition not equal","True")


        //*************************** OBJECT AND CPMPANION OBJECT *****************************************//

        Log.e("**","************** Object and Companion Object Example *************\n\n\n")

        Log.e("Id of Engineer:",""+Enginners.id)
        Log.e("Id of Engineer:",""+Enginners.collegeCode())
        Log.e("Id of IT:",""+College._id)


     //**************** CREATE LAMBDA FUNCTION AND PASS TO FUNCTION INSIDE CLASS AS A PARAMETER***************//

        var multiply=Multiplication()

        var result=0 //variable outside declaired

       // var mylambda : (Int,Int)-> Int = { x1:Int, y1:Int -> x1*y1}
                            //  OR
      //  var mylambda : (Int,Int)-> Int = { x1, y1 -> x1*y1}
                           //OR
      //  multiply.multiplyTwoNumber(2 ,4) { x1, y1 -> x1 * y1 }
                                                                //Lambda Expression or [Function]
                                                                  // x*y is method body
                                                                   // x and y are parmater
                                                             //(Int,Int) is type of parameter signature
        multiply.multiplyTwoNumber(2 ,4) { x1, y1 -> x1 * y1 }


    //***********************CLOUSURE INSIDE THE LAMBDA FUNCTION ***************************************//

        //Outside Variable can be change or Mutate inside the Lambda function.
        multiply.addNumber(3,4){y,z-> result=y+z} // result variable used inside the lambda function in kotlin. and
        // also can change the value of it .
        Log.e("Clouser resiult:",""+result)

    // ********************* WITH AND APPLY SCOPE KEYWORD IN KOTLIN **********************************//

        //access the variable inside of class by the use of "with" keyword
        //Their is no need to use of object again and again with every field of Class
        with(multiply){
            cal1=20
            cal2=11
        }

        //access the Method or Variables inside the Class by use of "apply" keyword
        //apply keyword returns the receiver
        multiply.apply {
            cal1=3
            cal2=4
        }.multiplyTwoNumber(3,4,{x1,y1-> x1*y1})



     //************************************ COLLECTIONS IN KOTLIN ***************************************//

        Log.e("**","************ COLLECTIONS  IN  KOTLIN ******************\n\n\n")
        // Mutable array having fixed size
        Log.e("**","************ 1.Array ******************")
        var arr=Array<String>(6) {"2"}
        arr[1]="3"
        arr[2]="4"
        arr[3]="5"
        for(index in 0..arr.size-1){
            Log.e("Arr element",""+arr[index])
        }

        //intArrayOf() or arrayOf()
        var arr1= intArrayOf(0,2,3,9,5)
        arr1[4]=4
        for (i in 0..arr1.size-1){
            Log.e("inArrayOf():",""+arr1[i])
        }

        var arr2= arrayOf<String>("Aa","Bb")

        for (i in 0..arr2.size-1){
            Log.e("arraOf<String>:",""+arr2[i])
        }



        //*****************************Immutable  List in Kotlin *****************************
        var imArrlist= listOf<Int>(2,3,4,5) //Immutable , Read only

        for(element in 0..imArrlist.size-1){
            Log.e("Immutable Arraylist",""+imArrlist.get(element)) //fetch using index of element
        }

        //******************************Mutable List in Kotlin*******************************

       // var mArrList = ArrayList<Int>(2,3,4,5) //Mutable , Read and Write, No fixed size
       // var mArrList = mutableListOf<Int>(2,3,4,5) //Mutable , Read and Write, No fixed size
       // var mArrList = arrayListOf<Int>(2,3,4,5) //Mutable , Read and Write, No fixed size
        var mArrList = arrayListOf<Int>(2,3,4,5) //Mutable , Read and Write, No fixed size

        mArrList.add(6)
        mArrList.add(7)
        mArrList.add(1,5)
        mArrList.remove(2)
        for (element in 0..mArrList.size-1){
            Log.e("Mutable Arraylist",""+mArrList[element])
        }

        //Immutable Hash map in kotlin
        var imuHasMap= mapOf<Int,String>(1 to "Mohit" ,2 to "Lucky", 3 to "Natani" ) //Immutable, only Read, fixed size
        for ( key in imuHasMap.keys){
            Log.e("Immutable HashMap:",""+imuHasMap[key])
        }

        //****************************Mutable Hash Map in Kotlin******************************

        //1.  var muHashMap= HashMap<Int,String>() // Mutable, Read and Write , No fixed size
        //2. var muHashMap= hashMapOf<Int,String>() // Mutable, Read and Write , No fixed size
        //3. var muHashMap= mutableMapOf<Int,String>() // Mutable, Read and Write , No fixed size
        var muHashMap= hashMapOf<Int,String>() // Mutable, Read and Write , No fixed size
        muHashMap.put(1 , "Sunil")
        muHashMap.put(2 , "Rahul")
        muHashMap.put(3 , "Mujaffar")

        muHashMap.put(1,"Vikas")
        for( key in muHashMap.keys){
            Log.e("mutable HashMap:",""+muHashMap[key])
        }

        //************************** Immutable Set in Kotlin ********************************
        var imSet= setOf<Int>(0,2,3,44,5,5,6,5) //having fixed size
        for ( element in imSet){
            Log.e("Immutable Set:",""+element)
        }

        //*************************** Mutable HashSet in Kotlin********************************
       //1.  var mHasSet = hashSetOf<Int>(1,2,3,3,45,5,4) //no fixed size
       // 2. var mHasSet = mutableSetOf<Int>(1,2,3,3,45,5,4) //no fixed size
       var mHasSet = mutableSetOf<Int>(1,2,3,3,45,5,4) //no fixed size
        mHasSet.add(6)
        for (element in mHasSet){
            Log.e("Mutable HashSet:",""+element)
        }

        //************************ FILTER AND MAP METHOD USING LAMBDA FUNCTION AND COLLECTION *****************************//
        //First we use Filter example
        val numb = listOf(2,3,4,5,66,56)
        val filNum= numb.filter { it < 10 } // OR { a -> a<10 } It is predicate,
        Log.e("**","************ Filter and Map ******************")
        Log.e("Filter of Numb:",""+filNum)
        // filter returns the result based on condition among list.
        //result of less than 10 numbers  will be get into filNum variable

        // Map method to perform operation on the element inside the list or collections
        val mapNum= numb.map { it * it} //OR {n -> n*n}
        Log.e("Map of Numb:",""+mapNum)

         // ********************** Scope function "With" ***********************
        Log.e("**","************ Scope function with ******************")
        //Property1 : Refer to context object by using 'this'
        //Property2: The return value is lambda function
        val strStringValue: Int = with(multiply){
            this._num+3

        }
        Log.e("_num is:-",""+strStringValue)
        //************************ Scope function "also"********************//
        //property 1: Refer Context object by using 'it'
        //property 2: Returns values of the context object
        val updateNumb= mArrList.also {
            Log.e("**","************Scope function 'also' ******************")
            Log.e("List Initial",""+it)

            it.add(8)
            Log.e("List after add:",""+it)

            it.remove(8)
            Log.e("List after remove",""+it)
        }

        Log.e("Also function log:-",""+updateNumb)

        //****************** Scope function 'let' **********************//

        //Property 1: Refer Context object by using it
        //Property 2: Return the values as a Lambad function.
         var str :String ? = "Mohit"

        str?.let {
            Log.e("**","*********** Scope function 'let'*******************\n\n\n")
            Log.e("strLet Reverse:-",""+it.reversed())
            Log.e("strLet length:-",""+it.length)
        }

        //********************* Scope function 'run' ***********************//
        //Property 1: Refer to Context Object by using 'this'.
        //Property 2: Return the value as  Lambda result.
        //run is the combination of with and let.
        //It is used to perform operation on nullable object and to remove 'NullPointerException'

        Log.e("**","************ Scope function 'run' ******************")
        var man1: Man? = null
        var bio=man1?.run {
            Log.e("name of man",""+this.name)
        }
        Log.e("string in run ",""+bio)

        //****************** Backing property **********************//
        Log.e("**","************ Backing property ******************")
        var man=Man()
        man.age1=23
        man.set(23)
        Log.e("Backing prop value=",""+man.get())


        //******************** NESTED CLASS ***************************//
        var outer=Outer()
        outer.i

        //create the object of Inside class inside the Outer Class
        //var inside=Outer.Nested()
        //inside.getValue()

        var nested= Outer().Nested()
        nested.getValue()
    }



    //Made Extension function inside the String Class
    infix fun String.addMessage( mes:String):String{
        return this + mes

    }



    //////////////////// Simple Class Message ///////////////////
    class Message{


        fun showMessage(mess:String):String{
           return  mess
        }
    }

    fun Message.addMessage1(mes1:String):String{

        return mes1
    }

    //////////////////// PRIMARY AND SECONDARY CONSTRUCTOR //////////

    /*
    * Class Student with primary and Secondary Constructor with Parameter
    * ALso show the functionality of declaring Property and init block
    * */
    class Student constructor ( var name:String){


        //Init block inside the class
        init {
            this.name=name
            Log.e("Messg init block is :-",""+this.name)
        }

        //Secondary constructor
        constructor( n:String, i:Int):this(n){
            //Body of secondary constructor is called after init block
            Log.e("The integer Value is:",""+i)
        }

    }

    //////////////////////////// FUNCTION AS EXPRESSION ///////////////////////

    //one line function or function as Expression
    fun max(a:Int, b: Int):Int = if(a>b) a else b

    ///////////////////////////// NAMED PARAMETER FUNCTION  ////////////////////
    fun namedFunction(length: Int,breadth: Int,height: Int=10){

        Log.e("length:",""+length);
        Log.e("breadth:",""+breadth);
        Log.e("height:",""+height);
    }

    ////////////////////////  INHERITANCE IN KOTLIN ///////////////////////////
    //Parent Class Animal
    open  class Animal{
        //open key word is used before the class name to remove class from final
        // which is by Default final in nature in kotlin

       open var color:String="whitish"
       open fun eat(){
            Log.e("String:","Animal Eating")
        }
    }

    //Derived Class Dog
    class  Dog : Animal(){
        var breed:String=""

       override var color:String="grey"

        fun Barking(){
            Log.e("Bark:","Dog is Barking")
        }

        //function is Overriden from super class
       override fun eat(){

            super<Animal>.eat()

            Log.e("Dog_String:","Dog is Eating")
        }
    }

    //Derived Class Cat
    class  Cat : Animal(){
        var age: Int=-1

        fun Meow(){
            Log.e("Meow","Cat is Doing Meow")
        }

        //function is Overriden from super class
       override fun eat(){
            Log.e("Cat_String:","Cat is Eating")
        }
    }

    //////////////// PRIMARY AND SECONDARY CONSTRUCTOR IN KOTLIN IN INHERITANCE //////////////

    //1. EXAMPLE FIRST
    open class Animal1{

       var color:String=""

       /* init {
            Log.e("Animal1 Color:-",""+color)
        }*/
        constructor( color:String){

            this.color= color
           Log.e("Sec Cons in Animal1",""+color)

        }
    }

    class Dog1 : Animal1{


        /*init {
            Log.e("DOg color:-",""+color)
        }*/

        constructor(color: String): super(color){
            this.color=color
            Log.e("Sec Cons in Dog1",""+color)
        }

    }

    //2. EXAMPLE SECOND
    open class Sister{

       constructor(sis_exp:String){
           Log.e("SisterExpertIn:-",""+sis_exp)
       }
    }

    class Brother : Sister{

        var expertIN:String=""
        constructor(expertIn:String,marksInEng:Int):super(expertIn){
            Log.e("BrotherExpIn:",""+expertIn)
            Log.e("MarksInEng:",""+marksInEng)

        }
    }

    //3. EXAMPLE THIRD (Primary Constructor with Inheritance)

   open class  Animal2(var color: String){

       init {
           Log.e("Animal2 Color is:-","${color}")
       }
    }

    class Dog2(var bread:String, color:String):Animal2(color){

        init {
            Log.e("Dog Class:","Dog has color ${color} and bread like ${bread}")
        }
    }

    //////////////////////////// ABSTRACT CLASS AND FUNCTION IN KOTLIN ///////////////////////

    abstract class School{ //You can not create the instance of abstract

     abstract var subject:String // properties inside the abstract class are also declare as abstract and not initialized

     abstract  fun Teacher() // function inside the abstract class are by default open in nature and not final

     open fun Student(){}//Normal function inside the abstract class are annotate with open keyword

     fun Class(){
         Log.e("Class:","Their are many section of class in School")
     }

    }

    class Eduction : School(){

        override var subject: String="English"
        override fun Teacher() {
           Log.e("Teacher:-","Teacher teaches in school")
        }

        override fun Student(){
            super.Class()
            Log.e("Student:","Student study in school")
        }

    }


    /////////////////////////// INTERFACES IN KOTLIN //////////////////////

    interface FirstInterface{ // Can't create the object of interface

        fun B()// It is an abstract method that only declared

        fun A(){

            Log.e("First Interface:","Here is A method in First Interface")
        } //Normal method of interface first i.e non abstract

    }

    interface SecondInterface{
        fun A(){
            Log.e("First Interface:","Here is A method in Second Interface")
        } //Normal method of interface second i.e non abstract
    }

    class ClassB : FirstInterface,SecondInterface{
        override fun B() {
            Log.e("Function B","Here is a func B declared in first Interface")
        }

        override fun A() {
           super<FirstInterface>.A()
           super<SecondInterface>.A()
        }


    }



    /////////////////// DATA CLASSES AND COPY METHOD IN KOTLIN /////////////////////////////

   data class Students(var name:String, var id:String){

    }

     class Juniors(var name:String, var id:String){

    }


    ///////////////////// OBJECT AND COMPANIOIN OBJECT IN KOTLIN //////////////////

    open class College{
      open  fun collegeCode(){
             Log.e("College Code","CS212")
         }

       companion object InfoTechnology{ //comapanion object within the class

           var _id: Int=2 //variable by defalult is static
       }
    }

    object Enginners : College(){ //object keywords does not required to create object or instance.
        // kotlin automatically creats class and object

        var id:Int=1; //by default the field act as a static

        fun engineerCode(){ //by default method act as a static method
            Log.e("Eng Code:","EN20")
        }

       override fun collegeCode(){

           super.collegeCode()
            Log.e("Code:-","College Code is CS212"+"Enginner Code is EN20")
        }
    }


    ///////////////////// KOTLIN LAMBDA FUNCTIONS AND HIGHER ORDER FUNCTION //////////////

    class Multiplication{

        var cal1:Int=0
        var cal2:Int=0

        var _val:String="Hello Multiply"
        var _num:Int=23

        fun  multiplyTwoNumber(a:Int,b:Int,action: (Int,Int)-> Int){ //Lambda with Higher Order Function

            var res = action(a,b)

            Log.e("Multiply a and b is",""+res)

        }

        //function of clouser example
        fun addNumber(a:Int,b:Int,action: (Int, Int) -> Unit){

            action(a,b) //here not any value returns
        }
    }


    class Man{
        var name: String="Rocky"
        var id: Int=22

        private var _age : Int= 0 //backing field
        var age1:Int=0 //actual field

        fun get() :Int{ //get the value of backing field
           return _age
        }

        fun set(age:Int){
            _age=age
        }
    }


    //Default Function in Kotlin
    @JvmOverloads
    fun findVolume(length: Int, breadth: Int, height: Int=50):Int{

        return length*breadth*height
    }

    ////////////////////// KOTLIN NESTED CLASS ////////////////////////////

    class Outer{

        var i=0

       inner class Nested{

            fun getValue(){
                Log.e("Value","Value of "+i)
            }
        }
    }



}