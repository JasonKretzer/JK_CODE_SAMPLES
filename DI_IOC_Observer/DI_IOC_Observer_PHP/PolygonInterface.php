<?php

interface PolygonInterface
{
    public function getPerimeter();
    public function getSideLength();
    public function setSideLength($newSideLength);
    public function getNumberOfSides();
}


?>