<?php

require_once "AbstractPolygon.php";

class Triangle extends AbstractPolygon implements PolygonInterface
{
    public function __construct($initSideLength) {
        $this->sides = 3;
        $this->setSideLength($initSideLength);
    }
    
}
?>