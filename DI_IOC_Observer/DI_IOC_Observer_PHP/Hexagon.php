<?php

require_once "AbstractPolygon.php";

class Hexagon extends AbstractPolygon implements PolygonInterface
{
    public function __construct($initSideLength) {
        $this->sides = 6;
        $this->setSideLength($initSideLength);
    }
    
}
?>