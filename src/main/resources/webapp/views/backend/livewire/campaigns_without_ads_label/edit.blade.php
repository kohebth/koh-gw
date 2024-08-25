<div class="row mt-4">
    <div class="col">
        <x-loading/>
        <form wire:submit.prevent='save' class="form-horizontal content-loading">
            {{ csrf_field() }}
            @include("backend.livewire.$moduleName.includes.inputs")
            <div class="row">
                <div class="col-12">
                    <div class="form-group">
                        <x-buttons.edit moduleName="{{$moduleName}}"> {{__('Update')}}</x-buttons.edit>
                        <x-buttons.cancel> {{__('Cancel')}}</x-buttons.cancel>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
