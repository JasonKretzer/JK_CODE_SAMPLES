using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DI_IOC_Observer_CS
{
    class Program
    {
        static void Main(string[] args) {
            Triangle triangle = new Triangle(5.0);
            Hexagon hexagon = new Hexagon(3);

            PerimeterCalculator calculator = new PerimeterCalculator();

            PolygonService triObserver1 = new PolygonService("TriObs1");
            PolygonService triObserver2 = new PolygonService("TriObs2");
            triangle.Subscribe(triObserver1);
            triangle.Subscribe(triObserver2);

            PolygonService hexObserver1 = new PolygonService("HexObs1");
            PolygonService hexObserver2 = new PolygonService("HexObs2");
            hexagon.Subscribe(hexObserver1);
            hexagon.Subscribe(hexObserver2);

            Console.WriteLine("INITIAL TRIANGLE");
            Console.WriteLine("triangle perimeter: " + triangle.getPerimeter());
            Console.WriteLine("triangle sides: " + triangle.getNumberOfSides());
            Console.WriteLine("triangle side length: " + triangle.getSideLength());
            Console.WriteLine("Triangle From the Calculator IoC: " + calculator.calculate(triangle));

            Console.WriteLine("==================================");

            Console.WriteLine("INITIAL HEXAGON");
            Console.WriteLine("hexagon perimeter: " + hexagon.getPerimeter());
            Console.WriteLine("hexagon sides: " + hexagon.getNumberOfSides());
            Console.WriteLine("hexagon side length: " + hexagon.getSideLength());
            Console.WriteLine("Hexagon From the Calculator IoC: " + calculator.calculate(hexagon));

            Console.WriteLine("==================================");
            Console.WriteLine("==================================");
            Console.WriteLine("==================================");
            Console.WriteLine("Making Changes to SideLength so that OnNext will fire.");
            triangle.setSideLength(10);
            hexagon.setSideLength(10);

            Console.WriteLine("==================================");
            Console.WriteLine("==================================");
            Console.WriteLine("==================================");

            Console.WriteLine("FINAL TRIANGLE");
            Console.WriteLine("triangle perimeter: " + triangle.getPerimeter());
            Console.WriteLine("triangle sides: " + triangle.getNumberOfSides());
            Console.WriteLine("triangle side length: " + triangle.getSideLength());
            Console.WriteLine("Triangle From the Calculator IoC: " + calculator.calculate(triangle));

            Console.WriteLine("==================================");

            Console.WriteLine("FINAL HEXAGON");
            Console.WriteLine("hexagon perimeter: " + hexagon.getPerimeter());
            Console.WriteLine("hexagon sides: " + hexagon.getNumberOfSides());
            Console.WriteLine("hexagon side length: " + hexagon.getSideLength());
            Console.WriteLine("Hexagon From the Calculator IoC: " + calculator.calculate(hexagon));
            Console.ReadKey();
        }
    }
}
