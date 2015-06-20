using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DI_IOC_Observer_CS
{
    class PolygonService : IObserver<AbstractPolygon>
    {
        private String serviceName;
    
        public PolygonService(String initServiceName) {
            setServiceName(initServiceName);
        }

        public void setServiceName(String newServiceName) {
            if(newServiceName == null) {
                throw new ArgumentNullException("newServiceName","Service name must not be null.");
            }
            serviceName = newServiceName;
        }

        public void OnCompleted() {
            Console.WriteLine("Finished Notifying");
        }

        public void OnError(Exception error) {
            Console.WriteLine(error.Message);
        }

        public void OnNext(AbstractPolygon polygon) {
            Console.WriteLine("Service Name: " + serviceName + " -- " + "Perimeter: " + polygon.getPerimeter());
        }

        
    }
}