<?php
require_once "PolygonInterface.php";

abstract class AbstractPolygon implements SplSubject, PolygonInterface
{
    private $sideLength = 1;
    private $observers = [];
    protected $sides = 1;

    
    public function setSideLength($newSideLength) {
        if(!is_numeric($newSideLength) || $newSideLength <= 0) {
            throw new InvalidArgumentException('Side length must be number greater than 0.');
        }
        $this->sideLength = $newSideLength;
        $this->notify();
    }
    
    public function getSideLength() {
        return $this->sideLength;
    }
    
    public function getPerimeter() {
        return ($this->sides*$this->sideLength);
    }
    
    
    public function getNumberOfSides() {
        return $this->sides;
    }
    
    /////////////////////////////////////////////
    //SplSubject Interface bolierplate functions
    /////////////////////////////////////////////
    
    public function attach(SplObserver $observer) { 
        $oid = spl_object_hash($observer);
        if (!isset($this->observers[$oid])) {
            $this->observers[$oid] = $observer;
        }
        return $this;
    }
     
    public function detach(SplObserver $observer) {
        $oid = spl_object_hash($observer);
        if (isset($this->observers[$oid])) {
            unset($this->observers[$oid]);
        }
        return $this;
    }
     
    public function notify() {
        foreach ($this->observers as $observer) {    
            $observer->update($this);
        }
    }
}
?>