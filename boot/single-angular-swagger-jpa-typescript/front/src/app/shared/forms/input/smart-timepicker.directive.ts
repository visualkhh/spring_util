import {Directive, ElementRef, OnInit} from '@angular/core';


@Directive({
  selector: '[smartTimepicker]'
})
export class SmartTimepickerDirective implements OnInit{

  constructor(private el: ElementRef) { }

  ngOnInit(){
    import('bootstrap-timepicker/js/bootstrap-timepicker.min.js').then(()=>{
      this.render()
    })
  }


  render(){
    $(this.el.nativeElement).timepicker();
  }
}
