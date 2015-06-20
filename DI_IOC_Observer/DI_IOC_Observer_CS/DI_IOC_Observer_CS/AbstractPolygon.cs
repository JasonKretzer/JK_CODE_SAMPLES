using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DI_IOC_Observer_CS
{
    class AbstractPolygon : AbstractObservable<AbstractPolygon>, PolygonInterface
    {
        private double sideLength = 0.0f;
        protected int sides = 0;

        public AbstractPolygon() {
            sideLength = 1.0;
            sides = 1;
        }

        public double getPerimeter() {
            return ((double)sides * sideLength);
        }
        public void setSideLength(double newSideLength) {
            if(newSideLength <= 0.0) {
                throw new ArgumentOutOfRangeException("newSideLength" , "Side length must be a number greater than 0.");
            }
            sideLength = newSideLength;
            this.Notify(this);
        }
        public double getSideLength() {
            return sideLength;
        }
        public int getNumberOfSides() {
            return sides;
        }
    }
}