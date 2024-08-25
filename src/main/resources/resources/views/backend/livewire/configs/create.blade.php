<div class="row mt-4">
    <div class="col">

        <form wire:submit.prevent="save" class="form-horizontal">

            {{ csrf_field() }}

            <div class="form-group row">
                {{ html()->label('Name <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('name') }}
                <div class="col-sm-10">
                    <input type="text" wire:model.defer="configs.name" class="form-control" placeholder="Name">
                    @error('configs.name') <x-error-message>{{ $message }}</x-error-message> @enderror
                </div>
            </div>

            <div class="form-group row">
                {{ html()->label('Value <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('value') }}
                <div class="col-sm-10">
                    <textarea type="text" wire:model.defer="configs.value" class="form-control" rows="4" placeholder="Value"></textarea>
                    @error('configs.value') <x-error-message>{{ $message }}</x-error-message> @enderror
                </div>
            </div>

            <div class="form-group row">
                {{ html()->label('Description <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('description') }}
                <div class="col-sm-10">
                    <input type="text" wire:model.defer="configs.description" class="form-control" placeholder="Description">
                    @error('configs.description') <x-error-message>{{ $message }}</x-error-message> @enderror
                </div>
            </div>

            <div class="form-group row">
                {{ html()->label('Status')->class('col-6 col-sm-2 form-control-label')->for('status') }}
                <div class="col-6 col-sm-10">
                    <input type="checkbox" wire:model.defer="configs.status" class="form-control" value="1" style="width: 40px">
                    @error('configs.status') <x-error-message>{{ $message }}</x-error-message> @enderror
                </div>
            </div>

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
