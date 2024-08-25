<div class="row mt-4">
    <div class="col">
        <form wire:submit.prevent='save' class="form-horizontal">
            {{ csrf_field() }}
            <div class="form-group row">
                {{ html()->label('Service Name <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('service_name') }}
                <div class="col-sm-10">
                    <select wire:model.defer="serviceName" class="form-control">
                        <option value=""></option>
                        @foreach($services as $name)
                            <option value="{{$name}}">{{$name}}</option>
                        @endforeach
                    </select>
                    @error('serviceName')<x-error-message>{{$message}}</x-error-message> @enderror
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="form-group">
                        <x-buttons.force-server-update moduleName="{{$moduleName}}"> {{__('Force To Update')}}</x-buttons.force-server-update>
                        <x-buttons.cancel> {{__('Cancel')}}</x-buttons.cancel>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
