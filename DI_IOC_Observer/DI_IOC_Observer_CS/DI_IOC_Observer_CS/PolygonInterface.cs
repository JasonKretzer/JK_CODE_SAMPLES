using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DI_IOC_Observer_CS
{
    public interface PolygonInterface
    {
        double getPerimeter();
        double getSideLength();
        void setSideLength(double newSideLength);
        int getNumberOfSides();
    }
}