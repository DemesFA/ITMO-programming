#ifndef BaseObject_h
#define BaseObject_h

class BaseObject{
    virtual const char* classname() = 0;
    virtual unsigned int size() = 0;
};

#endif //BaseObject_h


