using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DI_IOC_Observer_CS
{
    class Hexagon : AbstractPolygon
    {
        public Hexagon(double initSideLength) {
            sides = 6;
            setSideLength(initSideLength);
        }
    }
}
