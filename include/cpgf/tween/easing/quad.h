#ifndef CPGF_TWEEN_EASING_QUAD_H
#define CPGF_TWEEN_EASING_QUAD_H

#include "cpgf/tween/gtweencommon.h"

namespace cpgf {

struct QuadEase
{
public:
	static GTweenEaseType easeIn() {
		return &_easeIn;
	}
	
	static GTweenEaseType easeOut() {
		return &_easeOut;
	}
	
	static GTweenEaseType easeInOut() {
		return &_easeInOut;
	}
	
private:
	static GTweenNumber _easeIn(const GTweenEaseParam * param) {
		GTweenNumber t = param->current / param->total;
		return t * t;
	}
	
	static GTweenNumber _easeOut(const GTweenEaseParam * param) {
		GTweenNumber t = param->current / param->total;
		return - t * (t - 2);
	}
	
	static GTweenNumber _easeInOut(const GTweenEaseParam * param) {
		GTweenNumber t = param->current / (param->total * 0.5f);
		if(t < 1.0f) {
			return 0.5f * t * t;
		}
		else {
			t = t - 1.0f;
			return -0.5f * (t * (t - 2.0f) - 1.0f);
		}
	}
};


} // namespace cpgf


#endif
