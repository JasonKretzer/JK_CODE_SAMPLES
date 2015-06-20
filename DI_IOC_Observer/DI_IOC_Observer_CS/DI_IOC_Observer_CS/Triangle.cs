using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DI_IOC_Observer_CS
{
    class Triangle : AbstractPolygon
    {
        public Triangle(double initSideLength) {
            sides = 3;
            setSideLength(initSideLength);
        }
    }
}
