import {Directive, Input, ElementRef} from '@angular/core';


@Directive({
  selector: '[saKnob]'
})
export class KnobDirective {


  @Input() saKnob: any;
  constructor(private el: ElementRef) {
    import('jquery-knob').then(()=>{
      this.render()
    })
  }

  render(){
    $(this.el.nativeElement).knob(this.saKnob || {})
  }

}
