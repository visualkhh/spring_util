import {
  Directive,
  Input,
  ElementRef,
  OnInit,
  HostBinding,
  HostListener
} from "@angular/core";

import "script-loader!smartadmin-plugins/bower_components/bootstrapvalidator/dist/js/bootstrapValidator.min.js";

@Directive({
  selector: "[saBootstrapValidator]"
})
export class BootstrapValidatorDirective implements OnInit {
  @Input() saBootstrapValidator: any;

  @HostListener("submit")
  s = () => {
    const bootstrapValidator = this.$form.data("bootstrapValidator");
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) this.$form.submit();
    else return;
  };

  private $form: any;

  constructor(private el: ElementRef) {}

  ngOnInit() {
    this.$form = $(this.el.nativeElement);
    this.$form.bootstrapValidator(this.saBootstrapValidator || {});

    this.$form.submit(function(ev) {
      ev.preventDefault();
    });
  }
}
