<?php

class PerimeterCalculator
{
    public function calculate(PolygonInterface $polygon) {
        return $polygon->getPerimeter();
    }
}

?>