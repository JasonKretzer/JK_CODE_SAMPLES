﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DI_IOC_Observer_CS
{
    public class AbstractObservable<T> : IObservable<T>
    {
        private List<IObserver<T>> observers;

        public AbstractObservable() {
            observers = new List<IObserver<T>>();
        }

        protected void Notify(T obj) {
            foreach (IObserver<T> observer in observers) {
                observer.OnNext(obj);
            }
        }

        public IDisposable Subscribe(IObserver<T> observer) {
            observers.Add(observer);
            return new Unsubscriber(observers, observer);
        }

        private class Unsubscriber : IDisposable
        {
            private List<IObserver<T>> observers;
            private IObserver<T> observer;

            public Unsubscriber(List<IObserver<T>> observers, IObserver<T> observer) {
                this.observers = observers;
                this.observer = observer;
            }

            public void Dispose() {
                if (observer != null && observers.Contains(observer)) {
                    observers.Remove(observer);
                }
            }
        }
    }
}
/*

*/