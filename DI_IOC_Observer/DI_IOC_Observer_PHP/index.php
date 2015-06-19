<?php

require_once "Triangle.php";
require_once "Hexagon.php";
require_once "PerimeterCalculator.php";
require_once "PolygonService.php";

$triangle = new Triangle(5);
$hexagon = new Hexagon(3);

$calculator = new PerimeterCalculator();

$triObserver1 = new PolygonService("TriObs1");
$triObserver2 = new PolygonService("TriObs2");
$triangle->attach($triObserver1);
$triangle->attach($triObserver2);


$hexObserver1 = new PolygonService("HexObs1");
$hexObserver2 = new PolygonService("HexObs2");
$hexagon->attach($hexObserver1);
$hexagon->attach($hexObserver2);

echo '<h1>INITIAL TRIANGLE</h1>';
echo 'triangle perimeter: '.$triangle->getPerimeter().'<br/>';
echo 'triangle sides: '.$triangle->getNumberOfSides().'<br/>';
echo 'triangle side length: '.$triangle->getSideLength().'<br/>';
echo 'Triangle From the Caclulator IoC: '.$calculator->calculate($triangle);

echo '<hr>';

echo '<h1>INITIAL HEXAGON</h1>';
echo 'hexagon perimeter: '.$hexagon->getPerimeter().'<br/>';
echo 'hexagon sides: '.$hexagon->getNumberOfSides().'<br/>';
echo 'hexagon side length: '.$hexagon->getSideLength().'<br/>';
echo 'Hexagon From the Caclulator IoC: '.$calculator->calculate($hexagon);

echo '<hr>';

$triangle->setSideLength(10);
$hexagon->setSideLength(10);

echo '<h1>FINAL TRIANGLE</h1>';
echo 'triangle perimeter: '.$triangle->getPerimeter().'<br/>';
echo 'triangle sides: '.$triangle->getNumberOfSides().'<br/>';
echo 'triangle side length: '.$triangle->getSideLength().'<br/>';
echo 'Triangle From the Caclulator IoC: '.$calculator->calculate($triangle);

echo '<hr>';

echo '<h1>FINAL HEXAGON</h1>';
echo 'hexagon perimeter: '.$hexagon->getPerimeter().'<br/>';
echo 'hexagon sides: '.$hexagon->getNumberOfSides().'<br/>';
echo 'hexagon side length: '.$hexagon->getSideLength().'<br/>';
echo 'Hexagon From the Caclulator IoC: '.$calculator->calculate($hexagon);





?>