<?php
 
class PolygonService implements SplObserver
{
    private $serviceName = '';
    public function __construct($initServiceName) {
        $this->setServiceName($initServiceName);
    }
    
    public function setServiceName($newServiceName) {
        if((is_null($newServiceName)) || (!is_string($newServiceName) )) {
            throw new InvalidArgumentException('ServiceName must be a string.');
        }
        $this->serviceName = $newServiceName;
    }
    
    
    
    public function update(SplSubject $polygon) {
        $perimeter = $polygon->getPerimeter();
        //now do something with perimeter -- just send to error log
        error_log("ServiceName: ".$this->serviceName." -- Perimeter = ".$perimeter);
    }
    
    
}
?>