using System;

namespace HelloWorld
{
  class Program
  {
    static void Main(string[] args)
    {
      int num = 10;
      Console.WriteLine(num);
      num = 2147483647;
      double num2 = 9.92;

      char myletter = 'B';
      string myletterStr = "B";

      Console.WriteLine(myletter);
      Console.WriteLine(myletterStr);

      Console.WriteLine(num + num2);

      Console.Write("Hello World! ");
      Console.Write("I will print on the same line.");
    }
  }
}