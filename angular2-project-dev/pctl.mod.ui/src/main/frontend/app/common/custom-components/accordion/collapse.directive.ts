import { Directive, ElementRef, EventEmitter, HostBinding, Input, Output, Renderer } from '@angular/core';

@Directive({selector: '[collapse]'})
export class CollapseDirective{
  
  @HostBinding('style.display')
  public display:string;
  // shown
  @HostBinding('class.in')
  @HostBinding('attr.aria-expanded')
  public isExpanded:boolean = true;
  // hidden
  @HostBinding('attr.aria-hidden')
  public isCollapsed:boolean = false;
  // stale state
  @HostBinding('class.collapse')
  public isCollapse:boolean = true;

  @Input()
  public set collapse(value:boolean) {
    this.isExpanded = value;
    this.toggle();
  }

  public get collapse():boolean {
    return this.isExpanded;
  }
  
  protected _el:ElementRef;
  protected _renderer:Renderer;

  public constructor(_el:ElementRef, _renderer:Renderer) {
    this._el = _el;
    this._renderer = _renderer;
  }

  public toggle():void {
    if (this.isExpanded) {
      this.hide();
    } else {
      this.show();
    }
  }

  public hide():void {
    this.isCollapse = false;
    this.isExpanded = false;
    this.isCollapsed = true;
    this.isCollapse = true;
    this.display = 'none';
  }

  public show():void {
    this.isCollapse = false;
    this.isExpanded = true;
    this.isCollapsed = false;
    this.display = 'block';
    this.isCollapse = true;
    this._renderer.setElementStyle(this._el.nativeElement, 'overflow', 'visible');
    this._renderer.setElementStyle(this._el.nativeElement, 'height', 'auto');
  }

}