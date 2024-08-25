<div class="row mt-4">
    <div class="col">
        <form wire:submit.prevent='save' class="form-horizontal">
            {{ csrf_field() }}
            @include("backend.livewire.$moduleName.includes.inputs")
            <div class="row">
                <div class="col-12">
                    <div class="form-group">
                        <x-buttons.create moduleName="{{$moduleName}}"> {{__('Create')}}</x-buttons.create>
                        <x-buttons.cancel> {{__('Cancel')}}</x-buttons.cancel>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
