using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DI_IOC_Observer_CS
{
    public class PerimeterCalculator
    {
        public double calculate(PolygonInterface polygon) {
            return polygon.getPerimeter();
        }
    }
}
